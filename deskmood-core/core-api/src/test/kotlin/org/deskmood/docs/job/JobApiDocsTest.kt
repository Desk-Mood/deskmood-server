package org.deskmood.docs.job

import com.ninjasquad.springmockk.MockkBean
import org.deskmood.controller.job.JobController
import org.deskmood.docs.ApiDocsTest
import org.deskmood.domain.job.JobService
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest

@WebMvcTest(controllers = [JobController::class])
abstract class JobApiDocsTest : ApiDocsTest("job") {

    @MockkBean
    lateinit var jobService: JobService
}
