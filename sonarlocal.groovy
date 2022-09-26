#!/usr/bin/env groovy
def STATUS = ['SUCCESS': 'good', 'FAILURE': 'danger', 'UNSTABLE': 'danger', 'ABORTED': 'danger']

pipeline {
    agent { label '' }
    environment {
        VER = VersionNumber([versionNumberString : '${BUILD_YEAR}.${BUILD_MONTH}.${BUILD_DAY}.ARTECH${BUILDS_ALL_TIME}', projectStartDate : '2019-8-27']);
        imageName = "artech";
        dockerRegistry = "ram1993"
    }
    stages {

        stage('cloning repository') {
            steps {
                script{
                    currentBuild.displayName = VER
                }
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'ashok-git', url: 'https://github.com/ramanjiazuredevops/8am-sep.git']]])
            }    
        }//end SCM
	stage('build && SonarQube analysis') {
            steps {
            //def scannerhome = tool name: 'SonarQubeScanner'

            withEnv(["PATH=/usr/bin:/opt/jdk1.8.0_171/bin:/opt/sonarqube/sonar-scanner/bin/"]) {
           withSonarQubeEnv('sonar') {
                     sh "/opt/sonar-scanner/bin/sonar-scanner -Dsonar.projectKey=demo-project -Dsonar.sources=${WORKSPACE} -Dsonar.exclusions=**/*.java -Dsonar.host.url=http://34.212.230.14:9001 -Dsonar.login=9005e1bde9a0262e234c248e8e60e4b98e202907"
                }
              }
        }
        }

        
	     
    }//end stages

}//end pipeline