package com.eternalcode.core.feature.chat.messages;

import com.eternalcode.multification.notice.Notice;

public interface ChatMessages {

    Notice chatCleared();

    Notice commandNotFound();
}
