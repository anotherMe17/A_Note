package com.anotherme17.wxRobot.handle;

import com.anotherme17.wxRobot.model.GroupMessage;
import com.anotherme17.wxRobot.model.UserMessage;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Created by Administrator on 2017/9/4.
 */
public class SampleMessageHandler implements MessageHandle {

    /**
     * 保存微信消息
     *
     * @param msg
     */
    @Override
    public void wxSync(JsonObject msg) {
    }

    @Override
    public void userMessage(UserMessage userMessage) {
        if (null == userMessage || userMessage.isEmpty()) {
            return;
        }
        String text = userMessage.getText();
        JsonObject raw_msg = userMessage.getRawMsg();
        String toUid = raw_msg.get("FromUserName").getAsString();
        // 撤回消息
        if ("test_revoke".equals(text)) {
            JsonObject dic = userMessage.getWechatApi().wxSendMessage("这条消息将被撤回", toUid);
        } else if ("reply".equals(text)) {
            userMessage.sendText("自动回复", toUid);
        } else {
            String replayMsg = "接收到：" + text;
            userMessage.sendText(replayMsg, toUid);
        }
    }

    @Override
    public void groupMessage(GroupMessage groupMessage) {
        groupMessage.sendText("自动回复", groupMessage.getGroupId());
    }

    @Override
    public void groupMemberChange(String groupId, JsonArray memberList) {

    }

    @Override
    public void groupListChange(String groupId, JsonArray memberList) {

    }

}