def createOrderHeaders(ExecutionContext ec) {
    def parameters = ec.context
    def orderHeader = [
            orderDate: parameters.orderDate,
            partyId: parameters.partyId,
            shippingContactMechId: parameters.shippingContactMechId,
            billingContactMechId: parameters.billingContactMechId
    ]

    // Create OrderHeader record
    def orderId = ec.entity.makeValue("OrderHeader").set(orderHeader).createOrStore().get("orderId")

    // Process OrderItems
    parameters.orderItems.each { item ->
        def orderItem = [
                orderId: orderId,
                productId: item.productId,
                quantity: item.quantity as Integer,
                status: item.status
        ]
        ec.entity.makeValue("OrderItem").set(orderItem).createOrStore()
    }

    ec.context.orderId = orderId
    return
}
