package kotlin.io;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: Utils.kt */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u001a*\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002H\u0007\u001a*\u0010\r\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002H\u0007\u001a8\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\u001a\b\u0002\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013\u001a&\u0010\u0016\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0017\u001a\u00020\u0018\u001a\n\u0010\u0019\u001a\u00020\u000f*\u00020\u0002\u001a\u0012\u0010\u001a\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0002\u001a\u0012\u0010\u001a\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0001\u001a\n\u0010\u001c\u001a\u00020\u0002*\u00020\u0002\u001a\u001d\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00020\u001d*\b\u0012\u0004\u0012\u00020\u00020\u001dH\u0002¢\u0006\u0002\b\u001e\u001a\u0011\u0010\u001c\u001a\u00020\u001f*\u00020\u001fH\u0002¢\u0006\u0002\b\u001e\u001a\u0012\u0010 \u001a\u00020\u0002*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u0014\u0010\"\u001a\u0004\u0018\u00010\u0002*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u0012\u0010#\u001a\u00020\u0002*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u0012\u0010$\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0002\u001a\u0012\u0010$\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0001\u001a\u0012\u0010&\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0002\u001a\u0012\u0010&\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0001\u001a\u0012\u0010'\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0002\u001a\u0012\u0010'\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0001\u001a\u0012\u0010(\u001a\u00020\u0001*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u001b\u0010)\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002H\u0002¢\u0006\u0002\b*\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004\"\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\b\u0010\u0004¨\u0006+"}, d2 = {"extension", "", "Ljava/io/File;", "getExtension", "(Ljava/io/File;)Ljava/lang/String;", "invariantSeparatorsPath", "getInvariantSeparatorsPath", "nameWithoutExtension", "getNameWithoutExtension", "createTempDir", "prefix", "suffix", "directory", "createTempFile", "copyRecursively", "", "target", "overwrite", "onError", "Lkotlin/Function2;", "Ljava/io/IOException;", "Lkotlin/io/OnErrorAction;", "copyTo", "bufferSize", "", "deleteRecursively", "endsWith", "other", "normalize", "", "normalize$FilesKt__UtilsKt", "Lkotlin/io/FilePathComponents;", "relativeTo", TtmlNode.RUBY_BASE, "relativeToOrNull", "relativeToOrSelf", "resolve", "relative", "resolveSibling", "startsWith", "toRelativeString", "toRelativeStringOrNull", "toRelativeStringOrNull$FilesKt__UtilsKt", "kotlin-stdlib"}, k = 5, mv = {1, 6, 0}, xi = 49, xs = "kotlin/io/FilesKt")
/* loaded from: classes2.dex */
class FilesKt__UtilsKt extends FilesKt__FileTreeWalkKt {
    public static /* synthetic */ File createTempDir$default(String str, String str2, File file, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "tmp";
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            file = null;
        }
        return FilesKt.createTempDir(str, str2, file);
    }

    @Deprecated(message = "Avoid creating temporary directories in the default temp location with this function due to too wide permissions on the newly created directory. Use kotlin.io.path.createTempDirectory instead.")
    public static final File createTempDir(String str, String str2, File file) {
        Intrinsics.checkNotNullParameter(str, "prefix");
        File createTempFile = File.createTempFile(str, str2, file);
        createTempFile.delete();
        if (createTempFile.mkdir()) {
            Intrinsics.checkNotNullExpressionValue(createTempFile, "dir");
            return createTempFile;
        }
        throw new IOException("Unable to create temporary directory " + createTempFile + FilenameUtils.EXTENSION_SEPARATOR);
    }

    public static /* synthetic */ File createTempFile$default(String str, String str2, File file, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "tmp";
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            file = null;
        }
        return FilesKt.createTempFile(str, str2, file);
    }

    @Deprecated(message = "Avoid creating temporary files in the default temp location with this function due to too wide permissions on the newly created file. Use kotlin.io.path.createTempFile instead or resort to java.io.File.createTempFile.")
    public static final File createTempFile(String str, String str2, File file) {
        Intrinsics.checkNotNullParameter(str, "prefix");
        File createTempFile = File.createTempFile(str, str2, file);
        Intrinsics.checkNotNullExpressionValue(createTempFile, "createTempFile(prefix, suffix, directory)");
        return createTempFile;
    }

    public static final String getExtension(File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        String name = file.getName();
        Intrinsics.checkNotNullExpressionValue(name, "name");
        return StringsKt.substringAfterLast(name, (char) FilenameUtils.EXTENSION_SEPARATOR, "");
    }

    public static final String getInvariantSeparatorsPath(File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        if (File.separatorChar != '/') {
            String path = file.getPath();
            Intrinsics.checkNotNullExpressionValue(path, "path");
            return StringsKt.replace$default(path, File.separatorChar, (char) IOUtils.DIR_SEPARATOR_UNIX, false, 4, (Object) null);
        }
        String path2 = file.getPath();
        Intrinsics.checkNotNullExpressionValue(path2, "path");
        return path2;
    }

    public static final String getNameWithoutExtension(File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        String name = file.getName();
        Intrinsics.checkNotNullExpressionValue(name, "name");
        return StringsKt.substringBeforeLast$default(name, ".", (String) null, 2, (Object) null);
    }

    public static final String toRelativeString(File file, File file2) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(file2, TtmlNode.RUBY_BASE);
        String relativeStringOrNull$FilesKt__UtilsKt = toRelativeStringOrNull$FilesKt__UtilsKt(file, file2);
        if (relativeStringOrNull$FilesKt__UtilsKt != null) {
            return relativeStringOrNull$FilesKt__UtilsKt;
        }
        throw new IllegalArgumentException("this and base files have different roots: " + file + " and " + file2 + FilenameUtils.EXTENSION_SEPARATOR);
    }

    public static final File relativeTo(File file, File file2) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(file2, TtmlNode.RUBY_BASE);
        return new File(FilesKt.toRelativeString(file, file2));
    }

    public static final File relativeToOrSelf(File file, File file2) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(file2, TtmlNode.RUBY_BASE);
        String relativeStringOrNull$FilesKt__UtilsKt = toRelativeStringOrNull$FilesKt__UtilsKt(file, file2);
        return relativeStringOrNull$FilesKt__UtilsKt == null ? file : new File(relativeStringOrNull$FilesKt__UtilsKt);
    }

    public static final File relativeToOrNull(File file, File file2) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(file2, TtmlNode.RUBY_BASE);
        String relativeStringOrNull$FilesKt__UtilsKt = toRelativeStringOrNull$FilesKt__UtilsKt(file, file2);
        if (relativeStringOrNull$FilesKt__UtilsKt == null) {
            return null;
        }
        return new File(relativeStringOrNull$FilesKt__UtilsKt);
    }

    private static final String toRelativeStringOrNull$FilesKt__UtilsKt(File file, File file2) {
        FilePathComponents normalize$FilesKt__UtilsKt = normalize$FilesKt__UtilsKt(FilesKt.toComponents(file));
        FilePathComponents normalize$FilesKt__UtilsKt2 = normalize$FilesKt__UtilsKt(FilesKt.toComponents(file2));
        if (!Intrinsics.areEqual(normalize$FilesKt__UtilsKt.getRoot(), normalize$FilesKt__UtilsKt2.getRoot())) {
            return null;
        }
        int size = normalize$FilesKt__UtilsKt2.getSize();
        int size2 = normalize$FilesKt__UtilsKt.getSize();
        int i = 0;
        int min = Math.min(size2, size);
        while (i < min && Intrinsics.areEqual(normalize$FilesKt__UtilsKt.getSegments().get(i), normalize$FilesKt__UtilsKt2.getSegments().get(i))) {
            i++;
        }
        StringBuilder sb = new StringBuilder();
        int i2 = size - 1;
        if (i <= i2) {
            while (true) {
                int i3 = i2 - 1;
                if (Intrinsics.areEqual(normalize$FilesKt__UtilsKt2.getSegments().get(i2).getName(), "..")) {
                    return null;
                }
                sb.append("..");
                if (i2 != i) {
                    sb.append(File.separatorChar);
                }
                if (i2 == i) {
                    break;
                }
                i2 = i3;
            }
        }
        if (i < size2) {
            if (i < size) {
                sb.append(File.separatorChar);
            }
            String str = File.separator;
            Intrinsics.checkNotNullExpressionValue(str, "separator");
            CollectionsKt.joinTo$default(CollectionsKt.drop(normalize$FilesKt__UtilsKt.getSegments(), i), sb, str, null, null, 0, null, null, 124, null);
        }
        return sb.toString();
    }

    public static /* synthetic */ File copyTo$default(File file, File file2, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 8192;
        }
        return FilesKt.copyTo(file, file2, z, i);
    }

    public static final File copyTo(File file, File file2, boolean z, int i) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(file2, "target");
        if (file.exists()) {
            if (file2.exists()) {
                if (!z) {
                    throw new FileAlreadyExistsException(file, file2, "The destination file already exists.");
                } else if (!file2.delete()) {
                    throw new FileAlreadyExistsException(file, file2, "Tried to overwrite the destination, but failed to delete it.");
                }
            }
            if (!file.isDirectory()) {
                File parentFile = file2.getParentFile();
                if (parentFile != null) {
                    parentFile.mkdirs();
                }
                FileOutputStream fileInputStream = new FileInputStream(file);
                try {
                    FileInputStream fileInputStream2 = fileInputStream;
                    fileInputStream = new FileOutputStream(file2);
                    ByteStreamsKt.copyTo(fileInputStream2, fileInputStream, i);
                    th = null;
                } finally {
                    try {
                        throw th;
                    } finally {
                    }
                }
            } else if (!file2.mkdirs()) {
                throw new FileSystemException(file, file2, "Failed to create target directory.");
            }
            return file2;
        }
        throw new NoSuchFileException(file, null, "The source file doesn't exist.", 2, null);
    }

    public static /* synthetic */ boolean copyRecursively$default(File file, File file2, boolean z, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            function2 = FilesKt__UtilsKt$copyRecursively$1.INSTANCE;
        }
        return FilesKt.copyRecursively(file, file2, z, function2);
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x00ae A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x009e A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump */
    public static final boolean copyRecursively(java.io.File r11, java.io.File r12, boolean r13, kotlin.jvm.functions.Function2<? super java.io.File, ? super java.io.IOException, ? extends kotlin.io.OnErrorAction> r14) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            java.lang.String r0 = "target"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
            java.lang.String r0 = "onError"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            boolean r0 = r11.exists()
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x002e
            kotlin.io.NoSuchFileException r12 = new kotlin.io.NoSuchFileException
            r5 = 0
            r7 = 2
            r8 = 0
            java.lang.String r6 = "The source file doesn't exist."
            r3 = r12
            r4 = r11
            r3.<init>(r4, r5, r6, r7, r8)
            java.lang.Object r11 = r14.invoke(r11, r12)
            kotlin.io.OnErrorAction r12 = kotlin.io.OnErrorAction.TERMINATE
            if (r11 == r12) goto L_0x002c
            goto L_0x002d
        L_0x002c:
            r1 = r2
        L_0x002d:
            return r1
        L_0x002e:
            kotlin.io.FileTreeWalk r0 = kotlin.io.FilesKt.walkTopDown(r11)     // Catch: TerminateException -> 0x00de
            kotlin.io.FilesKt__UtilsKt$copyRecursively$2 r3 = new kotlin.io.FilesKt__UtilsKt$copyRecursively$2     // Catch: TerminateException -> 0x00de
            r3.<init>(r14)     // Catch: TerminateException -> 0x00de
            kotlin.jvm.functions.Function2 r3 = (kotlin.jvm.functions.Function2) r3     // Catch: TerminateException -> 0x00de
            kotlin.io.FileTreeWalk r0 = r0.onFail(r3)     // Catch: TerminateException -> 0x00de
            java.util.Iterator r0 = r0.iterator()     // Catch: TerminateException -> 0x00de
        L_0x0041:
            boolean r3 = r0.hasNext()     // Catch: TerminateException -> 0x00de
            if (r3 == 0) goto L_0x00dd
            java.lang.Object r3 = r0.next()     // Catch: TerminateException -> 0x00de
            java.io.File r3 = (java.io.File) r3     // Catch: TerminateException -> 0x00de
            boolean r4 = r3.exists()     // Catch: TerminateException -> 0x00de
            if (r4 != 0) goto L_0x0068
            kotlin.io.NoSuchFileException r10 = new kotlin.io.NoSuchFileException     // Catch: TerminateException -> 0x00de
            r6 = 0
            java.lang.String r7 = "The source file doesn't exist."
            r8 = 2
            r9 = 0
            r4 = r10
            r5 = r3
            r4.<init>(r5, r6, r7, r8, r9)     // Catch: TerminateException -> 0x00de
            java.lang.Object r3 = r14.invoke(r3, r10)     // Catch: TerminateException -> 0x00de
            kotlin.io.OnErrorAction r4 = kotlin.io.OnErrorAction.TERMINATE     // Catch: TerminateException -> 0x00de
            if (r3 != r4) goto L_0x0041
            return r2
        L_0x0068:
            java.lang.String r4 = kotlin.io.FilesKt.toRelativeString(r3, r11)     // Catch: TerminateException -> 0x00de
            java.io.File r5 = new java.io.File     // Catch: TerminateException -> 0x00de
            r5.<init>(r12, r4)     // Catch: TerminateException -> 0x00de
            boolean r4 = r5.exists()     // Catch: TerminateException -> 0x00de
            if (r4 == 0) goto L_0x00ae
            boolean r4 = r3.isDirectory()     // Catch: TerminateException -> 0x00de
            if (r4 == 0) goto L_0x0083
            boolean r4 = r5.isDirectory()     // Catch: TerminateException -> 0x00de
            if (r4 != 0) goto L_0x00ae
        L_0x0083:
            if (r13 != 0) goto L_0x0087
        L_0x0085:
            r4 = r1
            goto L_0x009c
        L_0x0087:
            boolean r4 = r5.isDirectory()     // Catch: TerminateException -> 0x00de
            if (r4 == 0) goto L_0x0094
            boolean r4 = kotlin.io.FilesKt.deleteRecursively(r5)     // Catch: TerminateException -> 0x00de
            if (r4 != 0) goto L_0x009b
            goto L_0x0085
        L_0x0094:
            boolean r4 = r5.delete()     // Catch: TerminateException -> 0x00de
            if (r4 != 0) goto L_0x009b
            goto L_0x0085
        L_0x009b:
            r4 = r2
        L_0x009c:
            if (r4 == 0) goto L_0x00ae
            kotlin.io.FileAlreadyExistsException r4 = new kotlin.io.FileAlreadyExistsException     // Catch: TerminateException -> 0x00de
            java.lang.String r6 = "The destination file already exists."
            r4.<init>(r3, r5, r6)     // Catch: TerminateException -> 0x00de
            java.lang.Object r3 = r14.invoke(r5, r4)     // Catch: TerminateException -> 0x00de
            kotlin.io.OnErrorAction r4 = kotlin.io.OnErrorAction.TERMINATE     // Catch: TerminateException -> 0x00de
            if (r3 != r4) goto L_0x0041
            return r2
        L_0x00ae:
            boolean r4 = r3.isDirectory()     // Catch: TerminateException -> 0x00de
            if (r4 == 0) goto L_0x00b8
            r5.mkdirs()     // Catch: TerminateException -> 0x00de
            goto L_0x0041
        L_0x00b8:
            r7 = 0
            r8 = 4
            r9 = 0
            r4 = r3
            r6 = r13
            java.io.File r4 = kotlin.io.FilesKt.copyTo$default(r4, r5, r6, r7, r8, r9)     // Catch: TerminateException -> 0x00de
            long r4 = r4.length()     // Catch: TerminateException -> 0x00de
            long r6 = r3.length()     // Catch: TerminateException -> 0x00de
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 == 0) goto L_0x0041
            java.io.IOException r4 = new java.io.IOException     // Catch: TerminateException -> 0x00de
            java.lang.String r5 = "Source file wasn't copied completely, length of destination file differs."
            r4.<init>(r5)     // Catch: TerminateException -> 0x00de
            java.lang.Object r3 = r14.invoke(r3, r4)     // Catch: TerminateException -> 0x00de
            kotlin.io.OnErrorAction r4 = kotlin.io.OnErrorAction.TERMINATE     // Catch: TerminateException -> 0x00de
            if (r3 != r4) goto L_0x0041
            return r2
        L_0x00dd:
            return r1
        L_0x00de:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__UtilsKt.copyRecursively(java.io.File, java.io.File, boolean, kotlin.jvm.functions.Function2):boolean");
    }

    public static final boolean deleteRecursively(File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        boolean z = true;
        while (true) {
            for (File file2 : FilesKt.walkBottomUp(file)) {
                if (file2.delete() || !file2.exists()) {
                    if (z) {
                        break;
                    }
                }
                z = false;
            }
            return z;
        }
    }

    public static final boolean startsWith(File file, File file2) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(file2, "other");
        FilePathComponents components = FilesKt.toComponents(file);
        FilePathComponents components2 = FilesKt.toComponents(file2);
        if (Intrinsics.areEqual(components.getRoot(), components2.getRoot()) && components.getSize() >= components2.getSize()) {
            return components.getSegments().subList(0, components2.getSize()).equals(components2.getSegments());
        }
        return false;
    }

    public static final boolean startsWith(File file, String str) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(str, "other");
        return FilesKt.startsWith(file, new File(str));
    }

    public static final boolean endsWith(File file, File file2) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(file2, "other");
        FilePathComponents components = FilesKt.toComponents(file);
        FilePathComponents components2 = FilesKt.toComponents(file2);
        if (components2.isRooted()) {
            return Intrinsics.areEqual(file, file2);
        }
        int size = components.getSize() - components2.getSize();
        if (size < 0) {
            return false;
        }
        return components.getSegments().subList(size, components.getSize()).equals(components2.getSegments());
    }

    public static final boolean endsWith(File file, String str) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(str, "other");
        return FilesKt.endsWith(file, new File(str));
    }

    public static final File normalize(File file) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        FilePathComponents components = FilesKt.toComponents(file);
        File root = components.getRoot();
        List<File> normalize$FilesKt__UtilsKt = normalize$FilesKt__UtilsKt(components.getSegments());
        String str = File.separator;
        Intrinsics.checkNotNullExpressionValue(str, "separator");
        return FilesKt.resolve(root, CollectionsKt.joinToString$default(normalize$FilesKt__UtilsKt, str, null, null, 0, null, null, 62, null));
    }

    private static final FilePathComponents normalize$FilesKt__UtilsKt(FilePathComponents filePathComponents) {
        return new FilePathComponents(filePathComponents.getRoot(), normalize$FilesKt__UtilsKt(filePathComponents.getSegments()));
    }

    private static final List<File> normalize$FilesKt__UtilsKt(List<? extends File> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (File file : list) {
            String name = file.getName();
            if (!Intrinsics.areEqual(name, ".")) {
                if (!Intrinsics.areEqual(name, "..")) {
                    arrayList.add(file);
                } else if (arrayList.isEmpty() || Intrinsics.areEqual(((File) CollectionsKt.last((List<? extends Object>) arrayList)).getName(), "..")) {
                    arrayList.add(file);
                } else {
                    arrayList.remove(arrayList.size() - 1);
                }
            }
        }
        return arrayList;
    }

    public static final File resolve(File file, File file2) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(file2, "relative");
        if (FilesKt.isRooted(file2)) {
            return file2;
        }
        String file3 = file.toString();
        Intrinsics.checkNotNullExpressionValue(file3, "this.toString()");
        String str = file3;
        if ((str.length() == 0) || StringsKt.endsWith$default((CharSequence) str, File.separatorChar, false, 2, (Object) null)) {
            return new File(Intrinsics.stringPlus(file3, file2));
        }
        return new File(file3 + File.separatorChar + file2);
    }

    public static final File resolve(File file, String str) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(str, "relative");
        return FilesKt.resolve(file, new File(str));
    }

    public static final File resolveSibling(File file, File file2) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(file2, "relative");
        FilePathComponents components = FilesKt.toComponents(file);
        return FilesKt.resolve(FilesKt.resolve(components.getRoot(), components.getSize() == 0 ? new File("..") : components.subPath(0, components.getSize() - 1)), file2);
    }

    public static final File resolveSibling(File file, String str) {
        Intrinsics.checkNotNullParameter(file, "<this>");
        Intrinsics.checkNotNullParameter(str, "relative");
        return FilesKt.resolveSibling(file, new File(str));
    }
}
