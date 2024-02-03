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
                    echo "Building..."
                    sh 'mvn --version'
                    sh 'mvn -B -DskipTests clean package'
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
