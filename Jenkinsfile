pipeline {
    agent any

    tools {
        maven 'Maven' // Ensure Maven is configured in Jenkins (Manage Jenkins -> Global Tool Configuration)
    }

    environment {
        DOCKER_CREDENTIALS_ID = 'dockerhub-credentials' // Replace with your Docker credentials ID
        IMAGE_NAME = 'realestate1234/gateway' // Replace with your Docker Hub repository name
//        KUBECONFIG_CREDENTIALS_ID = 'kube-config' // Kubernetes credentials ID in Jenkins
  //      K8S_DEPLOYMENT_FILE = 'gateway-service.yml' // Path to your Kubernetes deployment file
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm // Check out source code from the repository
            }
        }

        stage('Build and Test') {
            steps {
                sh 'mvn clean package -DskipTests' // Build the Spring Boot project and skip tests
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    def imageTag = "${IMAGE_NAME}:${env.BUILD_NUMBER}"
                    sh "docker build -t ${imageTag} ."
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKER_CREDENTIALS_ID) {
                        def imageTag = "${IMAGE_NAME}:${env.BUILD_NUMBER}"
                        sh "docker push ${imageTag}"
                    }
                }
            }
        }

       /* stage('Deploy to Kubernetes') {
            steps {
                script {
                    withCredentials([file(credentialsId: KUBECONFIG_CREDENTIALS_ID, variable: 'KUBECONFIG')]) {
                        sh "kubectl --kubeconfig=$KUBECONFIG apply -f ${K8S_DEPLOYMENT_FILE}"
                    }
                }
            }
        }*/
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}
