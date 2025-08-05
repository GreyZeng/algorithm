/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grey.algorithm;

// 本文件课上没有讲
// java同学可以使用FastReader进行快读，可以使用FastWriter进行快写，速度是很快的
// 如何使用可以参考main函数
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.InputMismatchException;

public class Code_0013_FastReaderWriter {

    public static void main(String[] args) {
        FastReader reader = new FastReader(System.in);
        FastWriter writer = new FastWriter(System.out);
        System.out.println("输入一个字符：");
        int cha = reader.readByte(); // reader会读到字符的ASCII码
        System.out.println("输入一个int类型的数字：");
        int num1 = reader.readInt(); // reader会读到该数字
        System.out.println("输入一个long类型的数字：");
        long num2 = reader.readLong(); // reader会读到该数字
        System.out.println("打印结果:");
        writer.println(cha);
        writer.println(num1);
        writer.println(num2);
        writer.close();// close方法包含flush，会把结果刷出去
    }

    // 快读
    public static class FastReader {

        InputStream is;
        private byte[] inbuf = new byte[1024];
        public int lenbuf = 0;
        public int ptrbuf = 0;

        public FastReader(final InputStream is) {
            this.is = is;
        }

        public int readByte() {
            if (lenbuf == -1) {
                throw new InputMismatchException();
            }
            if (ptrbuf >= lenbuf) {
                ptrbuf = 0;
                try {
                    lenbuf = is.read(inbuf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (lenbuf <= 0) {
                    return -1;
                }
            }
            return inbuf[ptrbuf++];
        }

        public int readInt() {
            return (int) readLong();
        }

        public long readLong() {
            long num = 0;
            int b;
            boolean minus = false;
            while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
				;
            if (b == '-') {
                minus = true;
                b = readByte();
            }
            while (true) {
                if (b >= '0' && b <= '9') {
                    num = num * 10 + (b - '0');
                } else {
                    return minus ? -num : num;
                }
                b = readByte();
            }
        }
    }

    // 快写
    public static class FastWriter {

        private static final int BUF_SIZE = 1 << 13;
        private final byte[] buf = new byte[BUF_SIZE];
        private OutputStream out;
        private Writer writer;
        private int ptr = 0;

        public FastWriter(Writer writer) {
            this.writer = new BufferedWriter(writer);
            out = new ByteArrayOutputStream();
        }

        public FastWriter(OutputStream os) {
            this.out = os;
        }

        public FastWriter(String path) {
            try {
                this.out = new FileOutputStream(path);
            } catch (FileNotFoundException e) {
                throw new RuntimeException("FastWriter");
            }
        }

        public FastWriter write(byte b) {
            buf[ptr++] = b;
            if (ptr == BUF_SIZE) {
                innerflush();
            }
            return this;
        }

        public FastWriter write(String s) {
            s.chars().forEach(c -> {
                buf[ptr++] = (byte) c;
                if (ptr == BUF_SIZE) {
                    innerflush();
                }
            });
            return this;
        }

        private static int countDigits(long l) {
            if (l >= 1000000000000000000L) {
                return 19;
            }
            if (l >= 100000000000000000L) {
                return 18;
            }
            if (l >= 10000000000000000L) {
                return 17;
            }
            if (l >= 1000000000000000L) {
                return 16;
            }
            if (l >= 100000000000000L) {
                return 15;
            }
            if (l >= 10000000000000L) {
                return 14;
            }
            if (l >= 1000000000000L) {
                return 13;
            }
            if (l >= 100000000000L) {
                return 12;
            }
            if (l >= 10000000000L) {
                return 11;
            }
            if (l >= 1000000000L) {
                return 10;
            }
            if (l >= 100000000L) {
                return 9;
            }
            if (l >= 10000000L) {
                return 8;
            }
            if (l >= 1000000L) {
                return 7;
            }
            if (l >= 100000L) {
                return 6;
            }
            if (l >= 10000L) {
                return 5;
            }
            if (l >= 1000L) {
                return 4;
            }
            if (l >= 100L) {
                return 3;
            }
            if (l >= 10L) {
                return 2;
            }
            return 1;
        }

        public FastWriter write(long x) {
            if (x == Long.MIN_VALUE) {
                return write("" + x);
            }
            if (ptr + 21 >= BUF_SIZE) {
                innerflush();
            }
            if (x < 0) {
                write((byte) '-');
                x = -x;
            }
            int d = countDigits(x);
            for (int i = ptr + d - 1; i >= ptr; i--) {
                buf[i] = (byte) ('0' + x % 10);
                x /= 10;
            }
            ptr += d;
            return this;
        }

        public FastWriter writeln(long x) {
            return write(x).writeln();
        }

        public FastWriter writeln() {
            return write((byte) '\n');
        }

        private void innerflush() {
            try {
                out.write(buf, 0, ptr);
                ptr = 0;
            } catch (IOException e) {
                throw new RuntimeException("innerflush");
            }
        }

        public void flush() {
            innerflush();
            try {
                if (writer != null) {
                    writer.write(((ByteArrayOutputStream) out).toString());
                    out = new ByteArrayOutputStream();
                    writer.flush();
                } else {
                    out.flush();
                }
            } catch (IOException e) {
                throw new RuntimeException("flush");
            }
        }

        public FastWriter println(long x) {
            return writeln(x);
        }

        public void close() {
            flush();
            try {
                out.close();
            } catch (Exception e) {
            }
        }

    }
}
