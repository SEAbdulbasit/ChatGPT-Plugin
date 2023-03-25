package com.github.seabdulbasit.chatgptplugin.toolWindow

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowEP
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.openapi.wm.ToolWindowManager
import com.intellij.ui.content.ContentFactory
import com.sun.javafx.tk.Toolkit
import javafx.application.Platform
import javafx.embed.swing.JFXPanel
import javafx.scene.Scene
import javafx.scene.web.WebView
import java.awt.BorderLayout
import javax.swing.JPanel
import javax.swing.SwingUtilities


class ChatGPTWidowFactory : ToolWindowFactory {
    private val contentFactory = ContentFactory.SERVICE.getInstance()

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        Platform.setImplicitExit(false)

        // Register the tool window factory
        val toolWindow = ToolWindowEP.EP_NAME.extensionList.first { it.key == "myToolWindow" }
        ToolWindowManager.getInstance(project).registerToolWindow(toolWindow)

        val panel = JFXPanel()
        val webView = WebView()
        panel.scene = Scene(webView)

        // Load the web page
        Platform.runLater {
            webView.engine.load("https://chat.openai.com/")
        }

        // Add the JFXPanel to the tool window
        SwingUtilities.invokeLater {
            toolWindow.component.add(panel)
            toolWindow.component.revalidate()
        }
    }
}

//
//
//
//class ChatGPTWidowFactory : ToolWindowFactory {
//    private val contentFactory = ContentFactory.SERVICE.getInstance()
//
//    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
//        val myToolWindow = MyToolWindow()
//        val content = contentFactory.createContent(myToolWindow.getContent(), null, false)
//        toolWindow.contentManager.addContent(content)
//    }
//
//    override fun shouldBeAvailable(project: Project): Boolean {
//        val toolWindow = ToolWindowManager.getInstance(project).getToolWindow("ChatGPT")
//        toolWindow?.show { }
//        return true
//    }
//
//    class MyToolWindow {
//
//        fun getContent(): SimpleToolWindowPanel {
//            val toolWindowPanel = SimpleToolWindowPanel(false, true)
//            val jfxPanel = JFXPanel()
//            toolWindowPanel.setContent(jfxPanel)
//
//            Platform.runLater {
//                val webView = WebView()
//                val scene = Scene(webView)
//                jfxPanel.scene = scene
//                webView.engine.load("https://chat.openai.com/")
//            }
//
//            return toolWindowPanel
//        }
//    }
//}