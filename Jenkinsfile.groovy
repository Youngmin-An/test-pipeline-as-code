pipeline {
    agent {
        kubernetes {
            containerTemplate{
                name 'python'
                image 'python:3.6-slim'
                command 'sleep'
                args '99d'
            }
        }
    }
    stages {
        stage('Clone') {
            steps {
                container('python'){
                    checkout 'develop'
                    sh 'ls -al'
                }
            }
        }
    }
}
