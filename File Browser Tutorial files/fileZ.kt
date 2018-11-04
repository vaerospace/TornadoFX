package com.example.demo.app

import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import javafx.stage.FileChooser
import tornadofx.*
import java.io.File

class FileChooserView : View() {
    override val root = borderpane()

    private val ef = arrayOf(FileChooser.ExtensionFilter("Document files (*.pdf, *.txt)", "*.pdf", "*.txt"))

    private lateinit var tfFN: TextField
    private lateinit var tfFA: TextArea

    init {
        with(root) {
            title = "FileChooserView"
            center = form {
                fieldset("File Choosers") {
                    field("File Single") {
                        hbox {
                            tfFN = textfield()
                            button("open") {
                                action {
                                    val fn: List<File> = chooseFile("Single + non/block",ef, FileChooserMode.Single)
                                    if (fn.isNotEmpty()) {
                                        tfFN.text = "${fn.first()}"
                                    }
                                }
                            }
                        }
                    }
                    field("Files Multiple") {
                        hbox {
                            tfFA = textarea()
                            button("open") {
                                action {
                                    val fn: List<File> = chooseFile("Multi + block", ef, FileChooserMode.Multi, root.scene.window)
                                    if (fn.isNotEmpty()) {
                                        tfFA.text = "$fn"
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

class FileChooserApp : App(FileChooserView::class) {}
fun main(args: Array<String>) {
    launch<FileChooserApp>(args)
}