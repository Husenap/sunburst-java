package com.github.husenap.sunburst;

import com.github.husenap.sunburst.window.Window;

public abstract class Application {
  private Window window = null;

  public void run() {
    window = new Window();
    init();

    float currentTime = getCurrentTime();
    float accumulator = 0f;
    float t = 0f;
    float dt = tickRate();

    while (!window.shouldClose()) {
      float newTime = getCurrentTime();
      float frameTime = Math.min((newTime - currentTime), 0.25f);
      currentTime = newTime;
      accumulator += frameTime;

      window.pollEvents();
      while (accumulator >= dt) {
        tick(t, dt);
        t += dt;
        accumulator -= dt;
      }

      float alpha = accumulator / dt;

      window.startFrame();
      render(alpha);
      window.endFrame();

      window.swapBuffers();
    }

    window.dispose();
  }

  protected void init() {
  }

  protected float tickRate() {
    return 1f / 60f;
  }

  protected abstract void tick(float t, float dt);

  protected abstract void render(float alpha);

  public static float getCurrentTime() {
    return System.nanoTime() / 1e9f;
  }
}
