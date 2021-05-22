pipeline {
    agent any

    environment {
        PORT						= '5443'
        EXPOSED_PORT				= '5443'
        IMAGE_NAME					= 'correct-an-address'
        GITHUB_USERNAME             = 'reloadedd'
        GITHUB_ACCESS_TOKEN         = credentials('a41ba52b-a90d-40ff-9336-8d3e3ccaad50')
        GITHUB_SSH_ACCESS_KEY_ID 	= '5ee65712-5c65-465c-a4b2-49b3ddf38429'
        GITHUB_REPOSITORY_NAME      = 'correct-an-address'
        GITHUB_REPOSITORY_URL 		= 'git@github.com:reloadedd/correct-an-address.git'
        JENKINS_URL                 = 'https://www.reloadedd.me:8443'
        JENKINS_PROJECT_NAME        = 'Correct an Address'
    }

    parameters {
        string(name: 'VERSION_NUMBER', description: 'The version number of the application')
    }

    options {
        skipDefaultCheckout(true)
    }

    stages {
        stage('Checkout from Github') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [
                        [name: '*/master']
                    ],
                    doGenerateSubmoduleConfigurations: false,
                    extensions: [],
                    submoduleCfg: [],
                    userRemoteConfigs: [
                        [
                            credentialsId: "${GITHUB_SSH_ACCESS_KEY_ID}",
                            url: "${GITHUB_REPOSITORY_URL}"
                        ]
                    ]
                ])
            }
        }

        stage('Dockerize application') {
            steps {
                // Package the Application
                // Because, by default, the `package` command runs the test (thus, doing the Test stage's job),
                // add the `-Dmaven.test.skip.exec` option to skip testing at the packaging stage.
                sh './mvnw package -Dmaven.test.skip.exec'
                archiveArtifacts 	allowEmptyArchive: false,
                                    artifacts: 'target/*.jar',
                                    caseSensitive: true,
                                    defaultExcludes: true,
                                    fingerprint: false,
                                    onlyIfSuccessful: true

                // Dockerize it!
                sh "cp ~/.here/credentials.properties ."
                sh "docker image build -t ${IMAGE_NAME}:${VERSION_NUMBER} ."
            }
        }

        stage('Deploy') {
            steps {
                sh """#!/bin/bash
                if [[ "\$(docker container ls --filter name=${IMAGE_NAME} -aq | wc -l)" == "1" ]]; then
                    docker container stop \$(docker container ls --filter name=${IMAGE_NAME} -aq) &&
                    docker rm --force \$(docker container ls --filter name=${IMAGE_NAME} -aq)
                fi

                docker run --detach --name=${IMAGE_NAME} -p ${PORT}:${EXPOSED_PORT} ${IMAGE_NAME}:${VERSION_NUMBER}
                """
            }
        }

        stage('Test') {
            steps {
                // Run the tests
                sh './mvnw test -Dmaven.test.failure.ignore=true'

                // JUnit Results
                junit 'target/surefire-reports/*.xml'
            }
        }
    }

    post {
        success {
            script {
                sh """#!/bin/bash

                curl "https://api.GitHub.com/repos/${GITHUB_USERNAME}/${GITHUB_REPOSITORY_NAME}/statuses/\$(git rev-parse HEAD)?access_token=\${GITHUB_ACCESS_TOKEN}" \
                -H "Content-Type: application/json" \
                -X POST \
                -d "{\\"state\\": \\"success\\", \\"context\\": \\"continuous-integration/jenkins\\", \\"description\\": \\"Jenkins\\", \\"target_url\\": \\"${JENKINS_URL}/job/${JENKINS_PROJECT_NAME}/$BUILD_NUMBER/console\\"}"
                """
            }
        }

        failure {
            script {
                sh """#!/bin/bash

                curl "https://api.GitHub.com/repos/${GITHUB_USERNAME}/${GITHUB_REPOSITORY_NAME}/statuses/\$(git rev-parse HEAD)?access_token=\${GITHUB_ACCESS_TOKEN}" \
                -H "Content-Type: application/json" \
                -X POST \
                -d "{\\"state\\": \\"failure\\", \\"context\\": \\"continuous-integration/jenkins\\", \\"description\\": \\"Jenkins\\", \\"target_url\\": \\"${JENKINS_URL}/job/${JENKINS_PROJECT_NAME}/$BUILD_NUMBER/console\\"}"
                """
            }
        }
    }
}