def call(serviceName) {

    input(
        message: "Approve ${serviceName} deployment to production?",
        ok: "Deploy to production"
    )
}
