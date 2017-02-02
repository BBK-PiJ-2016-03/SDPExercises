package decoupledwithfactory

trait MessageRenderer {
  def render

  def getMessageProvider: MessageProvider

  def setMessageProvider(provider: MessageProvider)
}