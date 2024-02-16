pipeline {
    agent any

    tools {
        maven "maven"
    }
//     agent {
//         dockerfile {
//             label "docker"
//             //args "-v /tmp/maven:/home/jenkins/.m2 -e MAVEN_CONFIG=/home/jenkins/.m2"
//         }
//     }

    stages {
//         stage('Clonar Repositorio') {
//             steps {
//                 git(
//                     url: "https://github.com/SantiagoCarnezis/ticketing.git",
//                     branch: "master",
//                     changelog: true,
//                     poll: true
//                 )
//             }
//         }

        stage('Listar Archivoss') {
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

        stage('Docker') {
            steps {
                sh "docker --version"
            }
        }

        stage('Dockerr 2') {
            steps {
                sh "docker build -t zazoo-image ."
                sh "docker run -d zazoo-image"
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
