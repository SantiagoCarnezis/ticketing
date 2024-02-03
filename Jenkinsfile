pipeline {
    agent any

    stages {
        stage('Clonar Repositorio') {
            steps {
                git 'tu_repositorio_git'
            }
        }

        stage('Construir') {
            steps {
                script {
                    sh 'mvn clean install'
                }
            }
        }

        stage('Pruebas') {
            steps {
                script {
                    sh 'mvn test'
                }
            }
        }

        stage('Desplegar') {
            steps {
                script {
                    // Agrega comandos para el despliegue según tu entorno
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
