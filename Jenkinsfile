pipeline {

    agent {
        docker {
            image 'maven:3.9.6-amazoncorretto-17-debian'
        }
    }

    tools {
        maven "maven"
    }

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
                    sh 'mvn --version'
                }
            }
        }

        stage('Construir') {
            steps {
                sh "mvn clean verify"
                sh 'ls -la target'
            }
        }

//         stage('Docker') {
//             steps {
//                 sh "docker --version"
//             }
//         }
//
//         stage('Dockerr 2') {
//             steps {
//                 sh "docker build -t zazoo-image ."
//                 sh "docker run -d zazoo-image"
//             }
//         }
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