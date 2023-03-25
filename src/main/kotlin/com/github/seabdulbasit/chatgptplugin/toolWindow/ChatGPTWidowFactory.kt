package com.github.seabdulbasit.chatgptplugin.toolWindow

import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.openapi.wm.ToolWindowManager
import com.intellij.ui.content.ContentFactory
import com.intellij.ui.jcef.JBCefBrowser
import java.awt.BorderLayout
import javax.swing.JPanel


class ChatGPTWidowFactory : ToolWindowFactory {

    init {
        thisLogger().warn("Don't forget to remove all non-needed sample code files with their corresponding registration entries in `plugin.xml`.")
    }

    private val contentFactory = ContentFactory.SERVICE.getInstance()

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val myToolWindow = MyToolWindow(toolWindow)
        val content = contentFactory.createContent(myToolWindow.getContent(), null, false)
        toolWindow.contentManager.addContent(content)
    }

    override fun shouldBeAvailable(project: Project): Boolean {
        val toolWindow = ToolWindowManager.getInstance(project).getToolWindow("Chat-GPT")
        toolWindow?.show { }
        return true
    }

    class MyToolWindow(toolWindow: ToolWindow) {

        fun getContent() = JPanel(BorderLayout()).apply {
            val webView = JBCefBrowser()
            webView.loadURL("https://chat.openai.com/")
            add(webView.component, BorderLayout.CENTER)
        }
    }
}
