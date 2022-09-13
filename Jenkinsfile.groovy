pipeline {
    agent {
        kubernetes {
            containerTemplate{
                name 'python-build'
                image 'illacast/jenkins-agent:0.1.0'
                command 'sleep'
                args '99d'
            }
        }
    }
    stages {
        stage('Build and deploy') {
            steps {
                container('python-build'){
                    sh "git config user.name 'semantic-release'"
                    sh "git config user.email 'semantic-release@jenkins'"
                    sh "semantic-release publish -D version_variable=setup.py:__version__ -D branch=develop -D commit_parser=semantic_release.history.scipy_parser -D commit_author='${env.GIT_AUTHOR}'"
                }
            }
        }
    }
}
