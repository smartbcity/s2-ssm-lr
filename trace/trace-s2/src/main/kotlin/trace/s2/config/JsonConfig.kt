package trace.s2.config

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JsonConfig {

	@Bean
	fun objectMapper(): ObjectMapper =  ObjectMapper()
		.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
		.setSerializationInclusion(JsonInclude.Include.NON_NULL)
		.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
		.registerModule(KotlinModule())
		.registerModule(JavaTimeModule())

}