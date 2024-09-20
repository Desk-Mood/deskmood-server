package org.deskmood.admin.docs.job

import com.ninjasquad.springmockk.MockkBean
import org.deskmood.admin.controller.job.AdminJobController
import org.deskmood.admin.docs.AdminApiDocsTest
import org.deskmood.admin.domain.job.AdminJobService
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest

@WebMvcTest(controllers = [AdminJobController::class])
abstract class AdminJobApiDocsTest : AdminApiDocsTest("adminJob") {

    @MockkBean
    protected lateinit var adminJobService: AdminJobService
}
