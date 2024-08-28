rootProject.name = "deskmood"

include("deskmood-core")
include("deskmood-core:core-api")
include("deskmood-core:core-domain")

include("deskmood-storage:core-db")

include("deskmood-clients")
include("deskmood-clients:kakao-client")

include("deskmood-support")
include("deskmood-support:monitoring")
include("deskmood-support:logging")

include("deskmood-common")
