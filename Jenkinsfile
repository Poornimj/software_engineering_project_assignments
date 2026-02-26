pipeline {
    agent any

    tools {
        jdk 'JDK 21'
        maven 'Maven_3'
    }

    environment {
        // Windows Docker path (same as teacher file)
        PATH = "C:\\Program Files\\Docker\\Docker\\resources\\bin;${env.PATH}"

        // Your Assignment 1 folder
        PROJECT_DIR = "Project_Assignment_1"

        // DockerHub settings (CHANGE repo to yours)
        DOCKERHUB_CREDENTIALS_ID = 'Docker_Hub'
        DOCKERHUB_REPO = 'poornimj/project_assignment_1'
        DOCKER_IMAGE_TAG = 'latest'
    }

    stages {

        stage('check') {
            steps {
                // If you use "Pipeline script from SCM", you can replace this with: checkout scm
                git branch: 'main', url: 'https://github.com/Poornimj/software_engineering_project_assignments.git'
            }
        }

        stage('build job: ') {
            steps {
                dir("${PROJECT_DIR}") {
                    bat 'mvn clean install'
                }
            }
        }

        stage('test') {
            steps {
                dir("${PROJECT_DIR}") {
                    bat 'mvn test'
                }
            }
        }

        stage('Report') {
            steps {
                dir("${PROJECT_DIR}") {
                    bat 'mvn jacoco:report'
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
            }
        }

        stage('Build Docker Image') {
            steps {
                dir("${PROJECT_DIR}") {
                    script {
                        docker.build("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}")
                    }
                }
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS_ID) {
                        docker.image("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}").push()
                    }
                }
            }
        }
    }
}