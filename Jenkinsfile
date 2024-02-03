pipeline {
    agent any

    stages {
        stage('Clonar Repositorio') {
            steps {
                git(
                    url: "https://github.com/SantiagoCarnezis/ticketing.git",
                    branch: "master",
                    changelog: true,
                    poll: true
                )
            }
        }

        stage('Construir') {
            steps {
                script {
                    echo "Building...."
                    sh 'mvn clean install'
                }
            }
        }

        stage('Pruebas') {
            steps {
                script {
                    echo "Testing..."
                    sh 'mvn test'
                }
            }
        }

        stage('Desplegar') {
            steps {
                script {
                    echo "Deploying..."
                }
            }
        }
    }

    post {
        success {
            echo '¡Pipeline ejecutado exitosamente!'
        }
        failure {
            echo 'Hubo un error en el pipeline. Revisar los registros para obtener más detalles.'
        }
    }
}
