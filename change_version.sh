#!/bin/bash

# -----------------------------------------------------------------------------------
# | Simple Bash script which will change the version of the Spring Boot Application |
# | from all the files that are being versioned.                                    |
# | Note: The script must be run from the root of the directory tree.               |
# |                                                                                 |
# | Author:  Ionuț Roșca                                                            |
# -----------------------------------------------------------------------------------

dockerfile='Dockerfile'
pom_xml='pom.xml'

# Read from stdin the new version
echo -n '[#] Please enter your new version =--> '
read -r new_version

# Grep the old version from the Dockerfile
previous_version="$(grep 'version' $dockerfile | grep -o -E '[0-9]+\.[0-9]+\.[0-9]+')"
echo "[##] Previous version was [$previous_version]"
echo -e "[##] Upgrading to [$new_version]...\n"

# Change version of the Dockerfile
sed -i "s/LABEL version=\"$previous_version\"/LABEL version=\"$new_version\"/" $dockerfile
sed -i "s/correct-an-address-${previous_version}/correct-an-address-${new_version}/g" $dockerfile
echo "[##] Changed [$dockerfile] version to [$new_version]"

# Change version of the pom.xml file
sed -i "s/<version>${previous_version}-SNAPSHOT<\/version>/<version>${new_version}-SNAPSHOT<\/version>/" $pom_xml
echo "[##] Changed [$pom_xml] version to [$new_version]"
