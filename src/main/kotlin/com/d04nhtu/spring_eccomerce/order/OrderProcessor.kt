package com.d04nhtu.spring_eccomerce.order

import com.d04nhtu.spring_eccomerce.model.Order
import com.d04nhtu.spring_eccomerce.model.OrderStatus
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.IanaLinkRelations
import org.springframework.hateoas.Link
import org.springframework.hateoas.LinkRelation
import org.springframework.hateoas.server.RepresentationModelProcessor
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.stereotype.Component
import java.net.URI
import java.net.URISyntaxException


/**
 * A [RepresentationModelProcessor] that takes an [Order] that has been wrapped by Spring Data REST into an
 * [EntityModel] and applies custom Spring HATEAOS-based [Link]s based on the state.
 *
 */
@Component
class OrderProcessor(private val configuration: RepositoryRestConfiguration) :
    RepresentationModelProcessor<EntityModel<Order>> {
    override fun process(model: EntityModel<Order>): EntityModel<Order> {
        val controller: CustomOrderController = methodOn(CustomOrderController::class.java)
        val basePath = configuration.basePath.toString()

        val entityModel = model.content
        if (entityModel != null) {
            // If PAID_FOR is valid, add a link to the `pay()` method
            if (OrderStatus.valid(entityModel.orderStatus, OrderStatus.PAID_FOR)) {
                model.add(
                    applyBasePath( //
                        linkTo(controller.pay(model.content!!.id)) //
                            .withRel(IanaLinkRelations.PAYMENT),  //
                        basePath
                    )
                )
            }

            // If CANCELLED is valid, add a link to the `cancel()` method
            if (OrderStatus.valid(entityModel.orderStatus, OrderStatus.CANCELLED)) {
                model.add(
                    applyBasePath( //
                        linkTo(controller.cancel(entityModel.id)) //
                            .withRel(LinkRelation.of("cancel")),  //
                        basePath
                    )
                )
            }

            // If FULFILLED is valid, add a link to the `fulfill()` method
            if (OrderStatus.valid(entityModel.orderStatus, OrderStatus.FULFILLED)) {
                model.add(
                    applyBasePath( //
                        linkTo(controller.fulfill(entityModel.id)) //
                            .withRel(LinkRelation.of("fulfill")),  //
                        basePath
                    )
                )
            }
        }

        return model
    }

    companion object {
        /**
         * Adjust the [Link] such that it starts at basePath.
         *
         * @param link - link presumably supplied via Spring HATEOAS
         * @param basePath - base path provided by Spring Data REST
         * @return new [Link] with these two values melded together
         */
        private fun applyBasePath(link: Link, basePath: String): Link {
            val uri: URI = link.toUri()
            var newUri: URI? = null
            try {
                newUri = URI(
                    uri.scheme, uri.userInfo, uri.host,  //
                    uri.port, basePath + uri.path, uri.query, uri.fragment
                )
            } catch (e: URISyntaxException) {
                e.printStackTrace()
            }
            return Link.of(newUri.toString(), link.rel)
        }
    }
}