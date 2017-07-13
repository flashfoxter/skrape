package nolambda.skrape

import nolambda.skrape.nodes.*
import kotlin.reflect.KClass

fun ParentElement.query(cssSelector: String, body: ElementBody): SkrapeElemenet =
        Query(cssSelector = cssSelector, body = body).apply {
            postCreate(this@query, this)
        }

fun ParentElement.attr(attrName: String): SkrapeElemenet =
        Attr(node = this, attrName = attrName).apply {
            postCreate(this@attr, this)
        }

fun createValueElement(parent: ParentElement, clazz: KClass<*>): SkrapeElemenet =
        Value(node = parent, clazz = clazz.java).apply {
            postCreate(parent, this)
        }

fun ParentElement.text(): SkrapeElemenet = createValueElement(this, String::class)
fun ParentElement.bool(): SkrapeElemenet = createValueElement(this, Boolean::class)
fun ParentElement.int(): SkrapeElemenet = createValueElement(this, Int::class)

internal fun postCreate(parent: ParentElement, child: SkrapeElemenet) {
    parent.children.add(child)
}

infix fun String.to(element: SkrapeElemenet): SkrapeElemenet = element.apply { name = this@to }
