pipeline {
    agent any
    triggers {
        pollSCM '* * * * *'
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn assemble'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Start Application') {
            steps {
                sh 'mvn bootRun'
            }
        }
    }
}
