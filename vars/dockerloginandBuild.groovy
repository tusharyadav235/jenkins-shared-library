def call (){
  withCredentials([usernamePassword(
                    'credentialsId':"dockerCred",
                    passwordVariable:"dockerHubPass",
                    usernameVariable:"dockerHubUser")]){
                sh  " docker login -u ${env.dockerHubUser} -p ${env.dockerHubPass} "
                 sh "docker build -t ${env.dockerHubUser}/myapp:latest ."
                  sh "docker push ${env.dockerHubUser}/myapp:latest"
            } 
}
