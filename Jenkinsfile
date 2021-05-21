pipeline {
    agent any

	environment {
		PORT						= '5443'
		EXPOSED_PORT				= '5443'
		IMAGE_NAME					= 'correct-an-address'
		GITHUB_SSH_ACCESS_KEY_ID 	= '5ee65712-5c65-465c-a4b2-49b3ddf38429'
		GITHUB_REPOSITORY_URL 		= 'git@github.com:reloadedd/correct-an-address.git'
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
}