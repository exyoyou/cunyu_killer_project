.class public final Lcom/lyokone/location/R$styleable;
.super Ljava/lang/Object;
.source "R.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/lyokone/location/R;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "styleable"
.end annotation


# static fields
.field public static final ActivityFilter:[I

.field public static final ActivityFilter_activityAction:I = 0x0

.field public static final ActivityFilter_activityName:I = 0x1

.field public static final ActivityRule:[I

.field public static final ActivityRule_alwaysExpand:I = 0x0

.field public static final Capability:[I

.field public static final Capability_queryPatterns:I = 0x0

.field public static final Capability_shortcutMatchRequired:I = 0x1

.field public static final ColorStateListItem:[I

.field public static final ColorStateListItem_alpha:I = 0x2

.field public static final ColorStateListItem_android_alpha:I = 0x1

.field public static final ColorStateListItem_android_color:I = 0x0

.field public static final CoordinatorLayout:[I

.field public static final CoordinatorLayout_Layout:[I

.field public static final CoordinatorLayout_Layout_android_layout_gravity:I = 0x0

.field public static final CoordinatorLayout_Layout_layout_anchor:I = 0x1

.field public static final CoordinatorLayout_Layout_layout_anchorGravity:I = 0x2

.field public static final CoordinatorLayout_Layout_layout_behavior:I = 0x3

.field public static final CoordinatorLayout_Layout_layout_dodgeInsetEdges:I = 0x4

.field public static final CoordinatorLayout_Layout_layout_insetEdge:I = 0x5

.field public static final CoordinatorLayout_Layout_layout_keyline:I = 0x6

.field public static final CoordinatorLayout_keylines:I = 0x0

.field public static final CoordinatorLayout_statusBarBackground:I = 0x1

.field public static final FontFamily:[I

.field public static final FontFamilyFont:[I

.field public static final FontFamilyFont_android_font:I = 0x0

.field public static final FontFamilyFont_android_fontStyle:I = 0x2

.field public static final FontFamilyFont_android_fontVariationSettings:I = 0x4

.field public static final FontFamilyFont_android_fontWeight:I = 0x1

.field public static final FontFamilyFont_android_ttcIndex:I = 0x3

.field public static final FontFamilyFont_font:I = 0x5

.field public static final FontFamilyFont_fontStyle:I = 0x6

.field public static final FontFamilyFont_fontVariationSettings:I = 0x7

.field public static final FontFamilyFont_fontWeight:I = 0x8

.field public static final FontFamilyFont_ttcIndex:I = 0x9

.field public static final FontFamily_fontProviderAuthority:I = 0x0

.field public static final FontFamily_fontProviderCerts:I = 0x1

.field public static final FontFamily_fontProviderFetchStrategy:I = 0x2

.field public static final FontFamily_fontProviderFetchTimeout:I = 0x3

.field public static final FontFamily_fontProviderPackage:I = 0x4

.field public static final FontFamily_fontProviderQuery:I = 0x5

.field public static final FontFamily_fontProviderSystemFontFamily:I = 0x6

.field public static final GradientColor:[I

.field public static final GradientColorItem:[I

.field public static final GradientColorItem_android_color:I = 0x0

.field public static final GradientColorItem_android_offset:I = 0x1

.field public static final GradientColor_android_centerColor:I = 0x7

.field public static final GradientColor_android_centerX:I = 0x3

.field public static final GradientColor_android_centerY:I = 0x4

.field public static final GradientColor_android_endColor:I = 0x1

.field public static final GradientColor_android_endX:I = 0xa

.field public static final GradientColor_android_endY:I = 0xb

.field public static final GradientColor_android_gradientRadius:I = 0x5

.field public static final GradientColor_android_startColor:I = 0x0

.field public static final GradientColor_android_startX:I = 0x8

.field public static final GradientColor_android_startY:I = 0x9

.field public static final GradientColor_android_tileMode:I = 0x6

.field public static final GradientColor_android_type:I = 0x2

.field public static final LoadingImageView:[I

.field public static final LoadingImageView_circleCrop:I = 0x0

.field public static final LoadingImageView_imageAspectRatio:I = 0x1

.field public static final LoadingImageView_imageAspectRatioAdjust:I = 0x2

.field public static final SignInButton:[I

.field public static final SignInButton_buttonSize:I = 0x0

.field public static final SignInButton_colorScheme:I = 0x1

.field public static final SignInButton_scopeUris:I = 0x2

.field public static final SplitPairFilter:[I

.field public static final SplitPairFilter_primaryActivityName:I = 0x0

.field public static final SplitPairFilter_secondaryActivityAction:I = 0x1

.field public static final SplitPairFilter_secondaryActivityName:I = 0x2

.field public static final SplitPairRule:[I

.field public static final SplitPairRule_clearTop:I = 0x0

.field public static final SplitPairRule_finishPrimaryWithSecondary:I = 0x1

.field public static final SplitPairRule_finishSecondaryWithPrimary:I = 0x2

.field public static final SplitPairRule_splitLayoutDirection:I = 0x3

.field public static final SplitPairRule_splitMinSmallestWidth:I = 0x4

.field public static final SplitPairRule_splitMinWidth:I = 0x5

.field public static final SplitPairRule_splitRatio:I = 0x6

.field public static final SplitPlaceholderRule:[I

.field public static final SplitPlaceholderRule_placeholderActivityName:I = 0x0

.field public static final SplitPlaceholderRule_splitLayoutDirection:I = 0x1

.field public static final SplitPlaceholderRule_splitMinSmallestWidth:I = 0x2

.field public static final SplitPlaceholderRule_splitMinWidth:I = 0x3

.field public static final SplitPlaceholderRule_splitRatio:I = 0x4


# direct methods
.method static constructor <clinit>()V
    .locals 4

    const/4 v0, 0x2

    new-array v1, v0, [I

    .line 319
    fill-array-data v1, :array_0

    sput-object v1, Lcom/lyokone/location/R$styleable;->ActivityFilter:[I

    const/4 v1, 0x1

    new-array v1, v1, [I

    const/4 v2, 0x0

    const v3, 0x7f04002e

    aput v3, v1, v2

    .line 322
    sput-object v1, Lcom/lyokone/location/R$styleable;->ActivityRule:[I

    new-array v1, v0, [I

    .line 324
    fill-array-data v1, :array_1

    sput-object v1, Lcom/lyokone/location/R$styleable;->Capability:[I

    const/4 v1, 0x3

    new-array v2, v1, [I

    .line 327
    fill-array-data v2, :array_2

    sput-object v2, Lcom/lyokone/location/R$styleable;->ColorStateListItem:[I

    new-array v2, v0, [I

    .line 331
    fill-array-data v2, :array_3

    sput-object v2, Lcom/lyokone/location/R$styleable;->CoordinatorLayout:[I

    const/4 v2, 0x7

    new-array v3, v2, [I

    .line 334
    fill-array-data v3, :array_4

    sput-object v3, Lcom/lyokone/location/R$styleable;->CoordinatorLayout_Layout:[I

    new-array v3, v2, [I

    .line 342
    fill-array-data v3, :array_5

    sput-object v3, Lcom/lyokone/location/R$styleable;->FontFamily:[I

    const/16 v3, 0xa

    new-array v3, v3, [I

    .line 350
    fill-array-data v3, :array_6

    sput-object v3, Lcom/lyokone/location/R$styleable;->FontFamilyFont:[I

    const/16 v3, 0xc

    new-array v3, v3, [I

    .line 361
    fill-array-data v3, :array_7

    sput-object v3, Lcom/lyokone/location/R$styleable;->GradientColor:[I

    new-array v0, v0, [I

    .line 374
    fill-array-data v0, :array_8

    sput-object v0, Lcom/lyokone/location/R$styleable;->GradientColorItem:[I

    new-array v0, v1, [I

    .line 377
    fill-array-data v0, :array_9

    sput-object v0, Lcom/lyokone/location/R$styleable;->LoadingImageView:[I

    new-array v0, v1, [I

    .line 381
    fill-array-data v0, :array_a

    sput-object v0, Lcom/lyokone/location/R$styleable;->SignInButton:[I

    new-array v0, v1, [I

    .line 385
    fill-array-data v0, :array_b

    sput-object v0, Lcom/lyokone/location/R$styleable;->SplitPairFilter:[I

    new-array v0, v2, [I

    .line 389
    fill-array-data v0, :array_c

    sput-object v0, Lcom/lyokone/location/R$styleable;->SplitPairRule:[I

    const/4 v0, 0x5

    new-array v0, v0, [I

    .line 397
    fill-array-data v0, :array_d

    sput-object v0, Lcom/lyokone/location/R$styleable;->SplitPlaceholderRule:[I

    return-void

    :array_0
    .array-data 4
        0x7f040022
        0x7f040024
    .end array-data

    :array_1
    .array-data 4
        0x7f0401e9
        0x7f04020d
    .end array-data

    :array_2
    .array-data 4
        0x10101a5
        0x101031f
        0x7f04002c
    .end array-data

    :array_3
    .array-data 4
        0x7f040181
        0x7f04023c
    .end array-data

    :array_4
    .array-data 4
        0x10100b3
        0x7f040186
        0x7f040187
        0x7f040188
        0x7f04018b
        0x7f04018c
        0x7f04018d
    .end array-data

    :array_5
    .array-data 4
        0x7f040137
        0x7f040138
        0x7f040139
        0x7f04013a
        0x7f04013b
        0x7f04013c
        0x7f04013d
    .end array-data

    :array_6
    .array-data 4
        0x1010532
        0x1010533
        0x101053f
        0x101056f
        0x1010570
        0x7f040135
        0x7f04013e
        0x7f04013f
        0x7f040140
        0x7f0402a9
    .end array-data

    :array_7
    .array-data 4
        0x101019d
        0x101019e
        0x10101a1
        0x10101a2
        0x10101a3
        0x10101a4
        0x1010201
        0x101020b
        0x1010510
        0x1010511
        0x1010512
        0x1010513
    .end array-data

    :array_8
    .array-data 4
        0x10101a5
        0x1010514
    .end array-data

    :array_9
    .array-data 4
        0x7f04009e
        0x7f040160
        0x7f040161
    .end array-data

    :array_a
    .array-data 4
        0x7f040075
        0x7f0400c1
        0x7f0401f7
    .end array-data

    :array_b
    .array-data 4
        0x7f0401e3
        0x7f040203
        0x7f040204
    .end array-data

    :array_c
    .array-data 4
        0x7f0400a0
        0x7f040131
        0x7f040132
        0x7f040229
        0x7f04022a
        0x7f04022b
        0x7f04022c
    .end array-data

    :array_d
    .array-data 4
        0x7f0401d9
        0x7f040229
        0x7f04022a
        0x7f04022b
        0x7f04022c
    .end array-data
.end method

.method private constructor <init>()V
    .locals 0

    .line 317
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method
