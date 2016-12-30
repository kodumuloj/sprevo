import play.api.test._

class WithDepsApplication() extends WithApplicationLoader

class WithDepsBrowser() extends WithBrowser(app = new WithDepsApplication().app)
