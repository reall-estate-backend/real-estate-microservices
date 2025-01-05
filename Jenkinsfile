pipeline {
    agent any

    environment {
        CLUSTER_NAME = 'EKS-3'
        REGION = 'eu-north-1'
        NAMESPACE = 'webapps'
        SERVER_URL = 'https://9D720AC0DA754D236D3DE9ACE79C9423.gr7.eu-north-1.eks.amazonaws.com'  // Your EKS URL
    }

    stages {
        stage('Deploy to Kubernetes') {
            steps {
                withKubeCredentials(kubectlCredentials: [[caCertificate: '', clusterName: CLUSTER_NAME, contextName:'', credentialsId: 'k8-token', namespace: NAMESPACE, serverUrl: SERVER_URL]]) {
                    sh "kubectl apply -f deployment-service.yml"
                }
            }
        }

        stage('Verify Deployment') {
            steps {
                withKubeCredentials(kubectlCredentials: [[caCertificate: '', clusterName: CLUSTER_NAME, contextName:'', credentialsId: 'k8-token', namespace: NAMESPACE, serverUrl: SERVER_URL]]) {
                    sh "kubectl get svc -n ${NAMESPACE}"
                }
            }
        }
    }
}
