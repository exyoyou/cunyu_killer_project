package com.google.android.gms.common.server.response;

import com.google.android.gms.common.server.response.FastParser;
import java.io.BufferedReader;
import java.io.IOException;

/* loaded from: classes4.dex */
final class zaf implements FastParser.zaa<String> {
    @Override // com.google.android.gms.common.server.response.FastParser.zaa
    public final /* synthetic */ String zah(FastParser fastParser, BufferedReader bufferedReader) throws FastParser.ParseException, IOException {
        return fastParser.zac(bufferedReader);
    }
}
