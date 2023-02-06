def build()  {
    // echo "INFO: ${message}"
    node {
        git url: "https://github.com/webdevprashant/javaapp"
        sh "mvn clean package"
        sh "sudo docker rm -f myjavaapp"
        sh "sudo docker build -t webdevprashant/javaapp:${BUILD_NUMBER} ."
        sh "sudo docker run  -d -p 1222:8080 --name myjavaapp webdevprashant/javaapp:${BUILD_NUMBER}" 
    }
}
    
def test()  {
    // echo "INFO: ${message}"
    node {
        // sh 'curl --silent http://192.168.143.54:1222/java-web-app/'
	echo "KW"
    }
    
}

def push()  {
    // echo "INFO: Pushing"
    node {
      def dockerImage = "webdevprashant/javaapp:${BUILD_NUMBER}"
      def dockerHubUsername = "webdevprashant"
      def dockerHubPassword = "Dockeristhegreattool@1"

      withDockerRegistry(credentialsId: "docker-hub-credentials", url: "https://index.docker.io/v1/") {
          sh "docker build -t ${dockerImage} ."
          sh "docker login -u ${dockerHubUsername} -p ${dockerHubPassword}"
          sh "docker push ${dockerImage}"
      }
    }
}
