This module handles talking through HTTP.
It also makes some assumptions about mobile connections:
- we'll want to retry failed connections a few times before reporting an error
- we expect timeouts to be longer than the default on mobile (TODO)
- we want errors to be reported as kotlin.Result and not exceptions
