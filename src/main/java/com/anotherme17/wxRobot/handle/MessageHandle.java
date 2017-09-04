package com.anotherme17.wxRobot.handle;

import com.anotherme17.wxRobot.model.GroupMessage;
import com.anotherme17.wxRobot.model.UserMessage;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Created by Administrator on 2017/9/4.
 */
public interface MessageHandle {
    /**
     * 保存微信消息
     *
     * @param msg
     */
    void wxSync(JsonObject msg);

    void userMessage(UserMessage userMessage);

    void groupMessage(GroupMessage groupMessage);

    void groupMemberChange(String groupId, JsonArray memberList);

    void groupListChange(String groupId, JsonArray memberList);
}
