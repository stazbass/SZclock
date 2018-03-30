package com.szclock.demo.render;

import com.szclock.demo.entities.RenderItem;

import java.util.List;

public interface Renderable {
    List<RenderItem> collectRenderItems();
}
