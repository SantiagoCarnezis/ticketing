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

        stage('Listar Archivos') {
            steps {
                script {
                    sh 'ls -la'
                }
            }
        }

        stage('Construir') {
            steps {
                withMaven {
                    sh 'mvn --version'
                    sh "mvn clean verify"
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
