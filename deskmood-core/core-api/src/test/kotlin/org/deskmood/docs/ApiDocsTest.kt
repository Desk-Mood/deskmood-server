package org.deskmood.docs

import io.mockk.junit5.MockKExtension
import io.wwan13.api.document.ApiDocumentContextBuilder
import io.wwan13.api.document.snippets.DATETIME
import io.wwan13.api.document.snippets.DocumentError
import io.wwan13.api.document.snippets.DocumentField
import io.wwan13.api.document.snippets.DocumentSummary
import io.wwan13.api.document.snippets.OBJECT
import io.wwan13.api.document.snippets.STRING
import io.wwan13.api.document.snippets.isTypeOf
import io.wwan13.api.document.snippets.whichMeans
import io.wwan13.implmockmvc.MockMvcApiDocsTest
import org.deskmood.controller.ApiControllerAdvice
import org.deskmood.docs.stub.StubUserIdResolver
import org.deskmood.error.ErrorType
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Import
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder

fun ApiDocumentContextBuilder.expectedErrorTypes(vararg errorType: ErrorType) {
    val errors = errorType.map {
        DocumentError(it.errorCode, it.message)
    }.toTypedArray()
    this.expectedErrors(*errors)
}

@ExtendWith(
    value = [
        RestDocumentationExtension::class,
        MockKExtension::class
    ]
)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Import(TestConfiguration::class)
abstract class ApiDocsTest(
    private val tag: String = DocumentSummary.DEFAULT_DOCUMENT_TAG
) : MockMvcApiDocsTest() {

    @Autowired
    lateinit var applicationContext: ApplicationContext

    @Autowired
    lateinit var restDocumentationContextProvider: RestDocumentationContextProvider

    override fun mockMvc(): MockMvc {
        return MockMvcBuilders
            .standaloneSetup(applicationContext.getBean("${tag}Controller"))
            .alwaysDo<StandaloneMockMvcBuilder>(MockMvcResultHandlers.print())
            .apply<StandaloneMockMvcBuilder>(
                MockMvcRestDocumentation.documentationConfiguration(restDocumentationContextProvider)
            )
            .setControllerAdvice(ApiControllerAdvice())
            .setCustomArgumentResolvers(StubUserIdResolver())
            .build()
    }

    override fun tag(): String {
        return tag
    }

    override fun commonResponseField(): List<DocumentField> {
        return listOf(
            "status" isTypeOf STRING whichMeans "응답 상태",
            "timestamp" isTypeOf DATETIME whichMeans "응답 시간",
            "data" isTypeOf OBJECT whichMeans "응답 데이터",
        )
    }

    protected fun typeOf(errorType: ErrorType): DocumentError {
        return DocumentError(errorType.errorCode, errorType.message)
    }
}
