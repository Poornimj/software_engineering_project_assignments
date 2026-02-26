pipeline {
    agent any

    environment {
        PROJECT_DIR = "Project_Assignment_1"
    }

    stages {
        stage('Declarative: Checkout SCM') {
            steps { checkout scm }
        }

        stage('check') {
            steps {
                dir(PROJECT_DIR) {
                    bat 'mvn -B -DskipTests=true clean package'
                }
            }
        }

        stage('build job:') {
            steps {
                dir(PROJECT_DIR) {
                    bat 'mvn -B clean install'
                }
            }
        }

        stage('test') {
            steps {
                dir(PROJECT_DIR) {
                    bat 'mvn -B test'
                }
            }
        }

        stage('Report') {
            steps {
                dir(PROJECT_DIR) {
                    bat 'mvn -B jacoco:report'
                }
            }
        }

        stage('Publish Test Results') {
            steps {
                junit "${PROJECT_DIR}/target/surefire-reports/*.xml"
            }
        }

        stage('Publish Coverage Report') {
            steps {
                jacoco()
                archiveArtifacts artifacts: "${PROJECT_DIR}/target/site/jacoco/**", fingerprint: true
            }
        }

        stage('Build Docker Image') {
            steps {
                dir(PROJECT_DIR) {
                    bat 'docker build -t tempconverter:latest .'
                }
            }
        }

        stage('Run Docker Image') {
            steps {
                bat 'docker run --rm tempconverter:latest'
            }
        }
    }
}