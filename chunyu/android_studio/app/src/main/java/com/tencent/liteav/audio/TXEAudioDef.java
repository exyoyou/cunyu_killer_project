package com.tencent.liteav.audio;

/* loaded from: classes2.dex */
public class TXEAudioDef {
    public static final int TXE_AAC_FRAME_LEN_MS = 21;
    public static final int TXE_AAC_SAMPLE_NUM = 1024;
    public static final int TXE_AEC_NONE = 0;
    public static final int TXE_AEC_SYSTEM = 1;
    public static final int TXE_AEC_TRAE = 2;
    public static final int TXE_AUDIO_COMMON_ERR_INVALID_PARAMS = -901;
    public static final int TXE_AUDIO_COMMON_ERR_OK = 0;
    public static final int TXE_AUDIO_MODE_RECEIVER = 1;
    public static final int TXE_AUDIO_MODE_SPEAKER = 0;
    public static final int TXE_AUDIO_NOTIFY_AUDIO_INFO = 1;
    public static final int TXE_AUDIO_PLAY_ERR_AUDIO_TYPE_NOT_SUPPORT = -102;
    public static final int TXE_AUDIO_PLAY_ERR_INVALID_STATE = -101;
    public static final int TXE_AUDIO_PLAY_ERR_NOT_CREATE_JIT = -105;
    public static final int TXE_AUDIO_PLAY_ERR_OK = 0;
    public static final int TXE_AUDIO_PLAY_ERR_REPEAT_OPTION = -104;
    public static final int TXE_AUDIO_PLAY_ERR_START_HW_DECODEC_FAILED = -103;
    public static final int TXE_AUDIO_RECORD_ERR_CAPTURE_DATA_INVALID = -4;
    public static final int TXE_AUDIO_RECORD_ERR_CUR_PLAYER_INVALID = -5;
    public static final int TXE_AUDIO_RECORD_ERR_CUR_RECORDER_INVALID = -106;
    public static final int TXE_AUDIO_RECORD_ERR_NOT_START = -3;
    public static final int TXE_AUDIO_RECORD_ERR_NO_MIC_PERMIT = -1;
    public static final int TXE_AUDIO_RECORD_ERR_OK = 0;
    public static final int TXE_AUDIO_RECORD_ERR_REPEAT_OPTION = -2;
    public static final int TXE_AUDIO_RECORD_FIRST_FRAME = -6;
    public static final int TXE_AUDIO_RECORD_RELEASE_MIC = -7;
    public static final int TXE_CHANGER_TYPE_0 = -1;
    public static final int TXE_DEFAULT_AEC_TYPE = 0;
    public static final int TXE_DEFAULT_BITS = 16;
    public static final int TXE_DEFAULT_BYTES = 2;
    public static final int TXE_DEFAULT_ENC_FORMAT = 10;
    public static final int TXE_DEFAULT_FRAME_LEN_MS = 21;
    public static final int TXE_DEFAULT_PLAY_CHANNEL = 2;
    public static final int TXE_DEFAULT_RECORD_CHANNEL = 1;
    public static final int TXE_DEFAULT_REVERB_TYPE = 0;
    public static final int TXE_DEFAULT_SAMPLERATE = 48000;
    public static final int TXE_DEFAULT_SAMPLE_NUM = 1024;
    public static final int TXE_EQUALIZATION_BAND_0 = 0;
    public static final int TXE_EQUALIZATION_BAND_1 = 1;
    public static final int TXE_EQUALIZATION_BAND_2 = 2;
    public static final int TXE_EQUALIZATION_BAND_3 = 3;
    public static final int TXE_EQUALIZATION_BAND_4 = 4;
    public static final int TXE_EQUALIZATION_BAND_5 = 5;
    public static final int TXE_EQUALIZATION_BAND_6 = 6;
    public static final int TXE_EQUALIZATION_BAND_7 = 7;
    public static final int TXE_EQUALIZATION_BAND_8 = 8;
    public static final int TXE_EQUALIZATION_BAND_9 = 9;
    public static final int TXE_EQUALIZATION_BAND_NUM = 10;
    public static final int TXE_EQUALIZATION_TYPE_ACOUSTIC = 10;
    public static final int TXE_EQUALIZATION_TYPE_BLUES = 3;
    public static final int TXE_EQUALIZATION_TYPE_CLASSIC = 4;
    public static final int TXE_EQUALIZATION_TYPE_COUNTRY = 9;
    public static final int TXE_EQUALIZATION_TYPE_CUSTOM = 11;
    public static final int TXE_EQUALIZATION_TYPE_DANCE = 2;
    public static final int TXE_EQUALIZATION_TYPE_ELECTRIC = 7;
    public static final int TXE_EQUALIZATION_TYPE_JAZZ = 5;
    public static final int TXE_EQUALIZATION_TYPE_NONE = 0;
    public static final int TXE_EQUALIZATION_TYPE_POP = 1;
    public static final int TXE_EQUALIZATION_TYPE_ROCK = 8;
    public static final int TXE_EQUALIZATION_TYPE_SLOW = 6;
    public static final int TXE_OPUS_FRAME_LEN_MS = 20;
    public static final int TXE_OPUS_SAMPLE_NUM = 960;
    public static final int TXE_REVERB_TYPE_Bright = 5;
    public static final int TXE_REVERB_TYPE_Custom = 255;
    public static final int TXE_REVERB_TYPE_Ethereal = 8;
    public static final int TXE_REVERB_TYPE_Hall = 3;
    public static final int TXE_REVERB_TYPE_KTV = 1;
    public static final int TXE_REVERB_TYPE_Low = 4;
    public static final int TXE_REVERB_TYPE_Magnetic = 7;
    public static final int TXE_REVERB_TYPE_Melodious = 10;
    public static final int TXE_REVERB_TYPE_Metal = 6;
    public static final int TXE_REVERB_TYPE_NONE = 0;
    public static final int TXE_REVERB_TYPE_Room = 2;
    public static final int TXE_REVERB_TYPE_Studio = 9;
    public static final int TXE_VOICE_CHANGER_BEASET = 7;
    public static final int TXE_VOICE_CHANGER_CHILD = 1;
    public static final int TXE_VOICE_CHANGER_ETHEREAL = 11;
    public static final int TXE_VOICE_CHANGER_FATTY = 8;
    public static final int TXE_VOICE_CHANGER_FOREIGNER = 6;
    public static final int TXE_VOICE_CHANGER_HEAVY_MACHINERY = 10;
    public static final int TXE_VOICE_CHANGER_HEAVY_METAL = 4;
    public static final int TXE_VOICE_CHANGER_LOLI = 2;
    public static final int TXE_VOICE_CHANGER_NONE = 0;
    public static final int TXE_VOICE_CHANGER_SICK = 5;
    public static final int TXE_VOICE_CHANGER_STRONG_CURRENT = 9;
    public static final int TXE_VOICE_CHANGER_UNCLE = 3;
    public static final int WARNING_AUDIO_DEVICE_CAN_NOT_CAPTURE_PCM = 10050;
    public static final int WARNING_AUDIO_DEVICE_CAPTURE_RESTART_FAILED = 10052;
    public static final int WARNING_AUDIO_DEVICE_CAPTURE_START_FAILED = 10046;
    public static final int WARNING_AUDIO_DEVICE_CAPTURE_STOP_FAILED = 10047;
    public static final int WARNING_AUDIO_DEVICE_PLAY_RESTART_FAILED = 10053;
    public static final int WARNING_AUDIO_DEVICE_PLAY_START_FAILED = 10048;
    public static final int WARNING_AUDIO_DEVICE_PLAY_STOP_FAILED = 10049;
    public static final int WARNING_AUDIO_DEVICE_VOLUME_TYPE_SWITCH_FAILED = 10054;
}
