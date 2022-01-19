package com.github.husenap.sunburst.window;

import org.lwjgl.glfw.*;
import org.lwjgl.system.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.system.MemoryStack.*;

public class Window {
  private long window;

  public Window() {
    initWindow();
  }

  private void initWindow() {
    GLFWErrorCallback.createPrint(System.err).set();
    if (!glfwInit()) {
      throw new IllegalStateException("Unable to initialize GLFW");
    }

    glfwDefaultWindowHints();
    glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
    glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

    window = glfwCreateWindow(800, 600, "Sunburst in Java", NULL, NULL);
    if (window == NULL) {
      throw new IllegalStateException("Failed to create GLFW window");
    }

    glfwMakeContextCurrent(window);
    glfwSwapInterval(1);

    glfwShowWindow(window);
  }

  public void dispose() {
    glfwFreeCallbacks(window);
    glfwDestroyWindow(window);
    glfwTerminate();
    glfwSetErrorCallback(null).free();
  }

  public boolean shouldClose() {
    return glfwWindowShouldClose(window);
  }

  public void pollEvents() {
    glfwPollEvents();
  }

  public void swapBuffers() {
    glfwSwapBuffers(window);
  }

  public void startFrame() {

  }

  public void endFrame() {

  }

}
