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
                    sh 'pip install python-semantic-release'
                    sh 'semantic-release publish -D version_variable=setup.py:__version__ -D branch=develop -D commit_parser=semantic_release.history.scipy_parser'
                }
            }
        }
    }
}
