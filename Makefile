TRACE_CASSANDRA_NAME	   	:= smartbcity/lrtzc-trace-s2-cassandra-api
TRACE_CASSANDRA_IMG	    	:= ${TRACE_CASSANDRA_NAME}:${VERSION}

TRACE_SSM_NAME	   	    := smartbcity/lrtzc-trace-s2-ssm-api
TRACE_SSM_IMG	        := ${TRACE_SSM_NAME}:${VERSION}

USECASE_SSM_NAME	   	:= smartbcity/lrtzc-usecase-s2-ssm-api
USECASE_SSM_IMG	    	:= ${USECASE_SSM_NAME}:${VERSION}

package: package-trace-cassandra package-trace-ssm package-usecase-ssm

push: push-trace-cassandra push-trace-ssm push-usecase-ssm

package-trace-cassandra:
	VERSION=${VERSION} ./gradlew :trace:trace-s2-cassandra-api:bootBuildImage --imageName=${TRACE_CASSANDRA_IMG}

push-trace-cassandra:
	@docker push ${TRACE_CASSANDRA_IMG}

package-trace-ssm:
	VERSION=${VERSION} ./gradlew :trace:trace-s2-ssm-api:bootBuildImage --imageName=${TRACE_SSM_IMG}

push-trace-ssm:
	@docker push ${TRACE_SSM_IMG}

package-usecase-ssm:
	VERSION=${VERSION} ./gradlew :usecase:usecase-s2-ssm-api:bootBuildImage --imageName=${USECASE_SSM_IMG}

push-usecase-ssm:
	@docker push ${USECASE_SSM_IMG}