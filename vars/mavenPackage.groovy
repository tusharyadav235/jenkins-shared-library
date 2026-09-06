def call (
  echo "Packaging application"

    dir('cart-service') {
        sh '''
            mvn package -DskipTests
        '''
    }

    echo "Packaging completed successfully"
)
