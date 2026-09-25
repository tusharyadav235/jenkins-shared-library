def call(imageName, imageTag){
  echo "Scanning Docker image"

   sh """
   trivy image \
   --severity HIGH,CRITICAL \
   --exit-code 0 \
   ${IMAGE_NAME}:${IMAGE_TAG}
        
      """
  
    echo "Docker image scan completed"
}
