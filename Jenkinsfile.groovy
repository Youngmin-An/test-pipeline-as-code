pipeline {
    agent {
        kubernetes {
            containerTemplate{
                name 'python-build'
                image 'python:3.6-slim'
                command 'sleep'
                args '99d'
            }
        }
    }
    stages {
        stage('Clone') {
            steps {
                container('python-build'){
                    sh 'ls -al'
                }
            }
        }
    }
}
