current_dir = $(shell pwd)
compose_file = "$(current_dir)/docker-compose.yaml"
TEST_LOG_LEVEL ?= "info"

test:	## Runs test
	@drone exec --trusted --env-file=.env --include=test --include=mongodb

clean:	## Cleans output
	@./mvnw clean

build-push-image:	## Builds Container Image
	@drone exec --trusted --env-file=.env

help: ## Show this help
	@echo Please specify a build target. The choices are:
	@grep -E '^[0-9a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | awk 'BEGIN {FS = ":.*?## "}; {printf "$(INFO_COLOR)%-30s$(NO_COLOR) %s\n", $$1, $$2}'

.PHONY: swaggo	test	start-db	clean	lint	vendor	help swaggo build-push-image
