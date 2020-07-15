package fr.arakne.swfmaploader.swf;

import com.jpexs.decompiler.flash.SwfOpenException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class SwfFileLoaderTest {
    private SwfFileLoader loader;

    @BeforeEach
    void setUp() {
        loader = new SwfFileLoader("file:./files", "./tmp");
    }

    @Test
    void loadFromActionScript() throws IOException {
        String actionScript = "id = 41;\n" +
            "width = 15;\n" +
            "height = 17;\n" +
            "backgroundNum = 71;\n" +
            "ambianceId = 6;\n" +
            "musicId = 115;\n" +
            "bOutdoor = 1;\n" +
            "capabilities = 0;\n" +
            "mapData = \"HhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaaHxbfeaaadyH3bfeaaaaaHhaaeoIaaaHhaaeaaadyHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhGaeaaaaaHhWaeqgaaaHhGaeaaaaaGhaaeoIaaabhaaeaaaaabhaaeaaaaabhaaeaaaaaHxbfeaaadyHhbfeaaadyHxG5eaaaaabhGaeaaaaaHhG7eaaaaabhGaeaaaaaHhaaeaaaaaHha7eaaadyHhWaeaaaaaHhGaeaaaaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaaHhbfeaaadyHNHfeoIaaaHxG5eaaaaaH3G7eoIaaaHxG7eoIaaabhaaeaaadyHha5eoIaaaHxG7eaaaaaHhGaeaaaaaHhaaeaaaaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhaaeaaaaaHhHfeaaaaaHxHfeaaaeBHxG5eoIaiFHNa7eaaadtbhGaeaaaaaH3a5eaaaaaGNa5eaaadtHhWaeaaaaaHhGaeaaaaaHhGaeaaaaaGhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhaaeaaaaaHhHfeaaaaaHNHfeaaaaaHxG5eaaaaabhGaeaaaaabhGaeaaaaaHNa5eoIadyHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaGhaaeaaaaaahaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaabhaaeaaaaabhGaeaaaaaH3HfeaaaaaHxG5eoIaaabhGaeaaaaaG3a6eaaadtHhGaeaaaaaHhWaeaaaaaHhGaeaaaaaHhaaeaaadyGhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaaHhaaeoIadtbhaaeaaaaaGxbfeaaadLHxG5eaaaaabhGaeaaaaaHxG7eaaaaaHhGaeaaaaaHhGaemHWaaHhGaeb4aaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaaHNbfeaaaaaHxa5eoIadtHNa7eaaadyHhGaeaaaaaHhWaeaaaaaHxyfeaaaaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaabhaaeaaaaabhaaeaaaaaHxbfeaaaaaGxa5eaaadKHhGaeaaaaaHhGaeaaaaaHhyaeb4WaaHNafeaaafCGhaaeoIaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaabhaaeaaaaabhaaeaaaaaHNbfeaaaaaHhaaeaaaaaHhGaeaaaaaHhWaeaaaaaHNqfeqgaaaHxGgebQaaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeoIaaabhaaeaaaaabhbfeaaaaaHNbfeaaaaaHhGaeaaaaaHhGaeaaaaaHhWaemHGaaH3yfeb0WaaHhGaeb5q4QGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaabhaaeoIaaabxbfeaaaaaHhaaeaaaaaHhGaeaaaaaHhWaeaaaaaHhWaemHGaaHhGaeb4GaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaabhaaeaaaaaHhaaeaaaaaHhGaeaaaaaHhGaeaaaiFHhGaeaaaaaHhGaeaaaaaHhGaeaaa4QGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaaHhaaeaaaaaHhGaeaaaaaHhWaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaa4QGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeoIaaabhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeb4WaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaaHhaaeaaaaaHhGaeaaaaaHhWaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaH3GfebOGaaGxageaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeb5WaaHhWgeaaaaaGxagebQaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaaHhaaeaaaaaHhGaeaaaaaHhWaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHNWgeaaaaaHxqgeqgaaaG3agebQaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaaHhWaeqgGaaHhWaem0aaaHhWaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeb5WaaHNWgeaaaaaHNWgeaaaaaGNageaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaaHhaaeaaaaaHhGaeaaaaaHhGaeaaaaaHhWaeaaaaaHhGaeaaaaaHhGaeaaaiFH3WfebLqaaH3WgeaaaaaHxWgeaaaaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhWaeaaaaaGhaaeaaaiQHhWaeb4WaaHhWfebLqaaHNWgeaaaaaHNW5ebPWaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaaHhaaeaaa4QHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhWaeaaaiFHhG9eaaaaaH3WfeaaaaaHNWfebLqaaH3Wfeb0qaaH3G5eaaaaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeoIaaabhaaeaaaaaHhGaeaaa4QHhGaeaaaaaHhGaeaaaaaHhGaeaaaiFHhWaeaaaaaHhGaemHGaaHhGaeb5qaaHxWfeaaaaaHhWaeb4qaaH3G5eaaaaaGhaaeaaaaabh4aeaaaaabhaaeaaaaabhaaeaaaaaHhaaeaaaaaHhGaeaaaaaHhGaeaaaaaGhaaeaaadKHhGaeaaaaaHhWaeaaaaaHhGaeaaaaaHhGaemHGiFHhWaeb4GaaHhGaeaaaaaH3G5eaaaaaGhaaeaaaaabhaaeaaaaabhaaeoIaaaGNbfeaaaaaHhGaeaaaaaHhGaeaaaaaHhaaeaaadyHhG9eaaaaaHhGaeaaaaaHhWaeaaaaaHhaaeaaadpHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaH3G5eaaaaaGhaaeaaaaabhaaeaaaaaGxbfeaaaaaHhaaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhWaeaaaiFHhG9eaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhG6eaaaaaGhaaeaaaaaG3bfeaaaaaHNa5eaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaiFHhWaeaaaaaHhWaeaaaaaHhWaeaaaaaHhGaeaaaiFH3G7eaaaaaHxG7eaaaaaG3ageaaaaaHNW5eqgaaaHhaaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhaaeaaadPHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhWaeaaaaaHhGaeaaaaaGNa7eaaadxHNGgeaaaaaHNW5ebOaaaHhaaeaaaaaHhGaeaaaeBHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaiFHhG9eaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhWaeaaaaaHhGaeaaaaaHhqaeqgaaaH3WgebQaaaHhWaeaaaaaHhaaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaemHWaaHhGaemHaaaHhGaem0aaaHhWaeaaaaaHhGaeaaaaaHhWaeb0WaaHhWaeaaaaaHhaaeaaaaaHhGaeaaaeAHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhWaeb5aaaHhqfeqgaaaHhGaeaaaaaHhGaeaaaaaHhWaeaaaeBHhWaeaaaaaHhWaeaaaaaHhGaeaaaiFHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhaaeb4WaaH3afeaaaaaHhaaeb5qaaHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhaaeaaaaa\";\n"
        ;

        InputStream stream = new ByteArrayInputStream(actionScript.getBytes());

        SwfMapStructure structure = loader.parseActionScript(stream);

        assertEquals(41, structure.id());
        assertEquals(15, structure.width());
        assertEquals(17, structure.height());
        assertEquals(71, structure.backgroundNum());
        assertEquals(6, structure.ambianceId());
        assertEquals(115, structure.musicId());
        assertTrue(structure.isOutdoor());
        assertEquals(0, structure.capabilities());
        assertEquals("HhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaaHxbfeaaadyH3bfeaaaaaHhaaeoIaaaHhaaeaaadyHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhGaeaaaaaHhWaeqgaaaHhGaeaaaaaGhaaeoIaaabhaaeaaaaabhaaeaaaaabhaaeaaaaaHxbfeaaadyHhbfeaaadyHxG5eaaaaabhGaeaaaaaHhG7eaaaaabhGaeaaaaaHhaaeaaaaaHha7eaaadyHhWaeaaaaaHhGaeaaaaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaaHhbfeaaadyHNHfeoIaaaHxG5eaaaaaH3G7eoIaaaHxG7eoIaaabhaaeaaadyHha5eoIaaaHxG7eaaaaaHhGaeaaaaaHhaaeaaaaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhaaeaaaaaHhHfeaaaaaHxHfeaaaeBHxG5eoIaiFHNa7eaaadtbhGaeaaaaaH3a5eaaaaaGNa5eaaadtHhWaeaaaaaHhGaeaaaaaHhGaeaaaaaGhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhaaeaaaaaHhHfeaaaaaHNHfeaaaaaHxG5eaaaaabhGaeaaaaabhGaeaaaaaHNa5eoIadyHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaGhaaeaaaaaahaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaabhaaeaaaaabhGaeaaaaaH3HfeaaaaaHxG5eoIaaabhGaeaaaaaG3a6eaaadtHhGaeaaaaaHhWaeaaaaaHhGaeaaaaaHhaaeaaadyGhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaaHhaaeoIadtbhaaeaaaaaGxbfeaaadLHxG5eaaaaabhGaeaaaaaHxG7eaaaaaHhGaeaaaaaHhGaemHWaaHhGaeb4aaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaaHNbfeaaaaaHxa5eoIadtHNa7eaaadyHhGaeaaaaaHhWaeaaaaaHxyfeaaaaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaabhaaeaaaaabhaaeaaaaaHxbfeaaaaaGxa5eaaadKHhGaeaaaaaHhGaeaaaaaHhyaeb4WaaHNafeaaafCGhaaeoIaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaabhaaeaaaaabhaaeaaaaaHNbfeaaaaaHhaaeaaaaaHhGaeaaaaaHhWaeaaaaaHNqfeqgaaaHxGgebQaaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeoIaaabhaaeaaaaabhbfeaaaaaHNbfeaaaaaHhGaeaaaaaHhGaeaaaaaHhWaemHGaaH3yfeb0WaaHhGaeb5q4QGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaabhaaeoIaaabxbfeaaaaaHhaaeaaaaaHhGaeaaaaaHhWaeaaaaaHhWaemHGaaHhGaeb4GaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaabhaaeaaaaaHhaaeaaaaaHhGaeaaaaaHhGaeaaaiFHhGaeaaaaaHhGaeaaaaaHhGaeaaa4QGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaaHhaaeaaaaaHhGaeaaaaaHhWaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaa4QGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeoIaaabhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeb4WaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaaHhaaeaaaaaHhGaeaaaaaHhWaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaH3GfebOGaaGxageaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeb5WaaHhWgeaaaaaGxagebQaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaaHhaaeaaaaaHhGaeaaaaaHhWaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHNWgeaaaaaHxqgeqgaaaG3agebQaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaaHhWaeqgGaaHhWaem0aaaHhWaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeb5WaaHNWgeaaaaaHNWgeaaaaaGNageaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaaHhaaeaaaaaHhGaeaaaaaHhGaeaaaaaHhWaeaaaaaHhGaeaaaaaHhGaeaaaiFH3WfebLqaaH3WgeaaaaaHxWgeaaaaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhWaeaaaaaGhaaeaaaiQHhWaeb4WaaHhWfebLqaaHNWgeaaaaaHNW5ebPWaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaaHhaaeaaa4QHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhWaeaaaiFHhG9eaaaaaH3WfeaaaaaHNWfebLqaaH3Wfeb0qaaH3G5eaaaaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeoIaaabhaaeaaaaaHhGaeaaa4QHhGaeaaaaaHhGaeaaaaaHhGaeaaaiFHhWaeaaaaaHhGaemHGaaHhGaeb5qaaHxWfeaaaaaHhWaeb4qaaH3G5eaaaaaGhaaeaaaaabh4aeaaaaabhaaeaaaaabhaaeaaaaaHhaaeaaaaaHhGaeaaaaaHhGaeaaaaaGhaaeaaadKHhGaeaaaaaHhWaeaaaaaHhGaeaaaaaHhGaemHGiFHhWaeb4GaaHhGaeaaaaaH3G5eaaaaaGhaaeaaaaabhaaeaaaaabhaaeoIaaaGNbfeaaaaaHhGaeaaaaaHhGaeaaaaaHhaaeaaadyHhG9eaaaaaHhGaeaaaaaHhWaeaaaaaHhaaeaaadpHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaH3G5eaaaaaGhaaeaaaaabhaaeaaaaaGxbfeaaaaaHhaaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhWaeaaaiFHhG9eaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhG6eaaaaaGhaaeaaaaaG3bfeaaaaaHNa5eaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaiFHhWaeaaaaaHhWaeaaaaaHhWaeaaaaaHhGaeaaaiFH3G7eaaaaaHxG7eaaaaaG3ageaaaaaHNW5eqgaaaHhaaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhaaeaaadPHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhWaeaaaaaHhGaeaaaaaGNa7eaaadxHNGgeaaaaaHNW5ebOaaaHhaaeaaaaaHhGaeaaaeBHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaiFHhG9eaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhWaeaaaaaHhGaeaaaaaHhqaeqgaaaH3WgebQaaaHhWaeaaaaaHhaaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaemHWaaHhGaemHaaaHhGaem0aaaHhWaeaaaaaHhGaeaaaaaHhWaeb0WaaHhWaeaaaaaHhaaeaaaaaHhGaeaaaeAHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhWaeb5aaaHhqfeqgaaaHhGaeaaaaaHhGaeaaaaaHhWaeaaaeBHhWaeaaaaaHhWaeaaaaaHhGaeaaaiFHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhaaeb4WaaH3afeaaaaaHhaaeb5qaaHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhaaeaaaaa", structure.mapData());
    }

    @Test
    void loadFromActionScriptInvalidData() {
        assertThrows(IllegalArgumentException.class, () -> loader.parseActionScript(new ByteArrayInputStream("id = 41".getBytes())));
        assertThrows(NumberFormatException.class, () -> loader.parseActionScript(new ByteArrayInputStream("id = aaaa;".getBytes())));
        assertThrows(IllegalArgumentException.class, () -> loader.parseActionScript(new ByteArrayInputStream("mapData = aaaa;".getBytes())));
    }

    @Test
    void loadUrlFile() throws IOException, InterruptedException {
        File file = new File("./files/41_0701241437.swf");

        SwfMapStructure structure = loader.load(file.toURI().toURL());

        assertEquals(41, structure.id());
        assertEquals(15, structure.width());
        assertEquals(17, structure.height());
        assertEquals(71, structure.backgroundNum());
        assertEquals(6, structure.ambianceId());
        assertEquals(115, structure.musicId());
        assertTrue(structure.isOutdoor());
        assertEquals(0, structure.capabilities());
        assertEquals("HhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaaHxbfeaaadyH3bfeaaaaaHhaaeoIaaaHhaaeaaadyHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhGaeaaaaaHhWaeqgaaaHhGaeaaaaaGhaaeoIaaabhaaeaaaaabhaaeaaaaabhaaeaaaaaHxbfeaaadyHhbfeaaadyHxG5eaaaaabhGaeaaaaaHhG7eaaaaabhGaeaaaaaHhaaeaaaaaHha7eaaadyHhWaeaaaaaHhGaeaaaaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaaHhbfeaaadyHNHfeoIaaaHxG5eaaaaaH3G7eoIaaaHxG7eoIaaabhaaeaaadyHha5eoIaaaHxG7eaaaaaHhGaeaaaaaHhaaeaaaaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhaaeaaaaaHhHfeaaaaaHxHfeaaaeBHxG5eoIaiFHNa7eaaadtbhGaeaaaaaH3a5eaaaaaGNa5eaaadtHhWaeaaaaaHhGaeaaaaaHhGaeaaaaaGhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhaaeaaaaaHhHfeaaaaaHNHfeaaaaaHxG5eaaaaabhGaeaaaaabhGaeaaaaaHNa5eoIadyHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaGhaaeaaaaaahaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaabhaaeaaaaabhGaeaaaaaH3HfeaaaaaHxG5eoIaaabhGaeaaaaaG3a6eaaadtHhGaeaaaaaHhWaeaaaaaHhGaeaaaaaHhaaeaaadyGhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaaHhaaeoIadtbhaaeaaaaaGxbfeaaadLHxG5eaaaaabhGaeaaaaaHxG7eaaaaaHhGaeaaaaaHhGaemHWaaHhGaeb4aaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaaHNbfeaaaaaHxa5eoIadtHNa7eaaadyHhGaeaaaaaHhWaeaaaaaHxyfeaaaaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaabhaaeaaaaabhaaeaaaaaHxbfeaaaaaGxa5eaaadKHhGaeaaaaaHhGaeaaaaaHhyaeb4WaaHNafeaaafCGhaaeoIaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaabhaaeaaaaabhaaeaaaaaHNbfeaaaaaHhaaeaaaaaHhGaeaaaaaHhWaeaaaaaHNqfeqgaaaHxGgebQaaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeoIaaabhaaeaaaaabhbfeaaaaaHNbfeaaaaaHhGaeaaaaaHhGaeaaaaaHhWaemHGaaH3yfeb0WaaHhGaeb5q4QGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaabhaaeoIaaabxbfeaaaaaHhaaeaaaaaHhGaeaaaaaHhWaeaaaaaHhWaemHGaaHhGaeb4GaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaabhaaeaaaaaHhaaeaaaaaHhGaeaaaaaHhGaeaaaiFHhGaeaaaaaHhGaeaaaaaHhGaeaaa4QGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaaHhaaeaaaaaHhGaeaaaaaHhWaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaa4QGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeoIaaabhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeb4WaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaaHhaaeaaaaaHhGaeaaaaaHhWaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaH3GfebOGaaGxageaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeb5WaaHhWgeaaaaaGxagebQaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaaHhaaeaaaaaHhGaeaaaaaHhWaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHNWgeaaaaaHxqgeqgaaaG3agebQaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaaHhWaeqgGaaHhWaem0aaaHhWaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeb5WaaHNWgeaaaaaHNWgeaaaaaGNageaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaaHhaaeaaaaaHhGaeaaaaaHhGaeaaaaaHhWaeaaaaaHhGaeaaaaaHhGaeaaaiFH3WfebLqaaH3WgeaaaaaHxWgeaaaaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhWaeaaaaaGhaaeaaaiQHhWaeb4WaaHhWfebLqaaHNWgeaaaaaHNW5ebPWaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaaHhaaeaaa4QHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhWaeaaaiFHhG9eaaaaaH3WfeaaaaaHNWfebLqaaH3Wfeb0qaaH3G5eaaaaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeoIaaabhaaeaaaaaHhGaeaaa4QHhGaeaaaaaHhGaeaaaaaHhGaeaaaiFHhWaeaaaaaHhGaemHGaaHhGaeb5qaaHxWfeaaaaaHhWaeb4qaaH3G5eaaaaaGhaaeaaaaabh4aeaaaaabhaaeaaaaabhaaeaaaaaHhaaeaaaaaHhGaeaaaaaHhGaeaaaaaGhaaeaaadKHhGaeaaaaaHhWaeaaaaaHhGaeaaaaaHhGaemHGiFHhWaeb4GaaHhGaeaaaaaH3G5eaaaaaGhaaeaaaaabhaaeaaaaabhaaeoIaaaGNbfeaaaaaHhGaeaaaaaHhGaeaaaaaHhaaeaaadyHhG9eaaaaaHhGaeaaaaaHhWaeaaaaaHhaaeaaadpHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaH3G5eaaaaaGhaaeaaaaabhaaeaaaaaGxbfeaaaaaHhaaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhWaeaaaiFHhG9eaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhG6eaaaaaGhaaeaaaaaG3bfeaaaaaHNa5eaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaiFHhWaeaaaaaHhWaeaaaaaHhWaeaaaaaHhGaeaaaiFH3G7eaaaaaHxG7eaaaaaG3ageaaaaaHNW5eqgaaaHhaaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhaaeaaadPHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhWaeaaaaaHhGaeaaaaaGNa7eaaadxHNGgeaaaaaHNW5ebOaaaHhaaeaaaaaHhGaeaaaeBHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaiFHhG9eaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhWaeaaaaaHhGaeaaaaaHhqaeqgaaaH3WgebQaaaHhWaeaaaaaHhaaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaemHWaaHhGaemHaaaHhGaem0aaaHhWaeaaaaaHhGaeaaaaaHhWaeb0WaaHhWaeaaaaaHhaaeaaaaaHhGaeaaaeAHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhWaeb5aaaHhqfeqgaaaHhGaeaaaaaHhGaeaaaaaHhWaeaaaeBHhWaeaaaaaHhWaeaaaaaHhGaeaaaiFHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhaaeb4WaaH3afeaaaaaHhaaeb5qaaHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhaaeaaaaa", structure.mapData());
    }

    @Test
    void loadInvalidSwf() {
        File file = new File("./files/not_swf_file");

        assertThrows(SwfOpenException.class, () -> loader.load(file.toURI().toURL()));
    }

    @Test
    void loadNotMapFile() {
        File file = new File("./files/not_map_file.swf");

        assertThrows(IllegalArgumentException.class, () -> loader.load(file.toURI().toURL()));
    }

    @Test
    void loadInvalidMapData() {
        File file = new File("./files/invalid_map_data.swf");

        assertThrows(IllegalArgumentException.class, () -> loader.load(file.toURI().toURL()));
    }

    @Test
    void load() throws IOException, InterruptedException {
        SwfMapStructure structure = loader.load(41, "0701241437", null);

        assertEquals(41, structure.id());
        assertEquals(15, structure.width());
        assertEquals(17, structure.height());
        assertEquals(71, structure.backgroundNum());
        assertEquals(6, structure.ambianceId());
        assertEquals(115, structure.musicId());
        assertTrue(structure.isOutdoor());
        assertEquals(0, structure.capabilities());
        assertEquals("HhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaaHxbfeaaadyH3bfeaaaaaHhaaeoIaaaHhaaeaaadyHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhGaeaaaaaHhWaeqgaaaHhGaeaaaaaGhaaeoIaaabhaaeaaaaabhaaeaaaaabhaaeaaaaaHxbfeaaadyHhbfeaaadyHxG5eaaaaabhGaeaaaaaHhG7eaaaaabhGaeaaaaaHhaaeaaaaaHha7eaaadyHhWaeaaaaaHhGaeaaaaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaaHhbfeaaadyHNHfeoIaaaHxG5eaaaaaH3G7eoIaaaHxG7eoIaaabhaaeaaadyHha5eoIaaaHxG7eaaaaaHhGaeaaaaaHhaaeaaaaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhaaeaaaaaHhHfeaaaaaHxHfeaaaeBHxG5eoIaiFHNa7eaaadtbhGaeaaaaaH3a5eaaaaaGNa5eaaadtHhWaeaaaaaHhGaeaaaaaHhGaeaaaaaGhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhaaeaaaaaHhHfeaaaaaHNHfeaaaaaHxG5eaaaaabhGaeaaaaabhGaeaaaaaHNa5eoIadyHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaGhaaeaaaaaahaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaabhaaeaaaaabhGaeaaaaaH3HfeaaaaaHxG5eoIaaabhGaeaaaaaG3a6eaaadtHhGaeaaaaaHhWaeaaaaaHhGaeaaaaaHhaaeaaadyGhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaaHhaaeoIadtbhaaeaaaaaGxbfeaaadLHxG5eaaaaabhGaeaaaaaHxG7eaaaaaHhGaeaaaaaHhGaemHWaaHhGaeb4aaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaaHNbfeaaaaaHxa5eoIadtHNa7eaaadyHhGaeaaaaaHhWaeaaaaaHxyfeaaaaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaabhaaeaaaaabhaaeaaaaaHxbfeaaaaaGxa5eaaadKHhGaeaaaaaHhGaeaaaaaHhyaeb4WaaHNafeaaafCGhaaeoIaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaabhaaeaaaaabhaaeaaaaaHNbfeaaaaaHhaaeaaaaaHhGaeaaaaaHhWaeaaaaaHNqfeqgaaaHxGgebQaaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeoIaaabhaaeaaaaabhbfeaaaaaHNbfeaaaaaHhGaeaaaaaHhGaeaaaaaHhWaemHGaaH3yfeb0WaaHhGaeb5q4QGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaabhaaeoIaaabxbfeaaaaaHhaaeaaaaaHhGaeaaaaaHhWaeaaaaaHhWaemHGaaHhGaeb4GaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaabhaaeaaaaaHhaaeaaaaaHhGaeaaaaaHhGaeaaaiFHhGaeaaaaaHhGaeaaaaaHhGaeaaa4QGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaaHhaaeaaaaaHhGaeaaaaaHhWaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaa4QGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeoIaaabhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeb4WaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaaHhaaeaaaaaHhGaeaaaaaHhWaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaH3GfebOGaaGxageaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaabhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeb5WaaHhWgeaaaaaGxagebQaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaaHhaaeaaaaaHhGaeaaaaaHhWaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHNWgeaaaaaHxqgeqgaaaG3agebQaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaabhGaeaaaaaHhWaeqgGaaHhWaem0aaaHhWaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeb5WaaHNWgeaaaaaHNWgeaaaaaGNageaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaaHhaaeaaaaaHhGaeaaaaaHhGaeaaaaaHhWaeaaaaaHhGaeaaaaaHhGaeaaaiFH3WfebLqaaH3WgeaaaaaHxWgeaaaaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhWaeaaaaaGhaaeaaaiQHhWaeb4WaaHhWfebLqaaHNWgeaaaaaHNW5ebPWaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeaaaaaHhaaeaaa4QHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhWaeaaaiFHhG9eaaaaaH3WfeaaaaaHNWfebLqaaH3Wfeb0qaaH3G5eaaaaaGhaaeaaaaabhaaeaaaaabhaaeaaaaabhaaeoIaaabhaaeaaaaaHhGaeaaa4QHhGaeaaaaaHhGaeaaaaaHhGaeaaaiFHhWaeaaaaaHhGaemHGaaHhGaeb5qaaHxWfeaaaaaHhWaeb4qaaH3G5eaaaaaGhaaeaaaaabh4aeaaaaabhaaeaaaaabhaaeaaaaaHhaaeaaaaaHhGaeaaaaaHhGaeaaaaaGhaaeaaadKHhGaeaaaaaHhWaeaaaaaHhGaeaaaaaHhGaemHGiFHhWaeb4GaaHhGaeaaaaaH3G5eaaaaaGhaaeaaaaabhaaeaaaaabhaaeoIaaaGNbfeaaaaaHhGaeaaaaaHhGaeaaaaaHhaaeaaadyHhG9eaaaaaHhGaeaaaaaHhWaeaaaaaHhaaeaaadpHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaH3G5eaaaaaGhaaeaaaaabhaaeaaaaaGxbfeaaaaaHhaaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhWaeaaaiFHhG9eaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhG6eaaaaaGhaaeaaaaaG3bfeaaaaaHNa5eaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaiFHhWaeaaaaaHhWaeaaaaaHhWaeaaaaaHhGaeaaaiFH3G7eaaaaaHxG7eaaaaaG3ageaaaaaHNW5eqgaaaHhaaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhaaeaaadPHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhWaeaaaaaHhGaeaaaaaGNa7eaaadxHNGgeaaaaaHNW5ebOaaaHhaaeaaaaaHhGaeaaaeBHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaiFHhG9eaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhWaeaaaaaHhGaeaaaaaHhqaeqgaaaH3WgebQaaaHhWaeaaaaaHhaaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaemHWaaHhGaemHaaaHhGaem0aaaHhWaeaaaaaHhGaeaaaaaHhWaeb0WaaHhWaeaaaaaHhaaeaaaaaHhGaeaaaeAHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhGaeaaaaaHhWaeb5aaaHhqfeqgaaaHhGaeaaaaaHhGaeaaaaaHhWaeaaaeBHhWaeaaaaaHhWaeaaaaaHhGaeaaaiFHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhaaeb4WaaH3afeaaaaaHhaaeb5qaaHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhaaeaaaaaHhaaeaaaaa", structure.mapData());
        assertEquals("0701241437", structure.version());
    }

    @Test
    void loadIdNotMatch() {
        assertThrows(IllegalArgumentException.class, () -> loader.load(44, "0701241437", null));
    }

    @Test
    void loadWithKey() throws IOException, InterruptedException {
        SwfMapStructure structure = loader.load(42, "0706131721", "my key");

        assertEquals(42, structure.id());
        assertEquals(15, structure.width());
        assertEquals(17, structure.height());
        assertEquals(71, structure.backgroundNum());
        assertEquals(6, structure.ambianceId());
        assertEquals(115, structure.musicId());
        assertTrue(structure.isOutdoor());
        assertEquals(0, structure.capabilities());
        assertEquals("3c36484b5f3f12122a0a1c5451080b275b264d182a2a4e0f27065c3e1f212b00473f240a313833354d215b3a4a59235e571b3b511f451b162d0f57274b4f5e5e5e4110545c08243e281d14345f415d1b333128380810010b530d2432265e2e3d303e5e51703b180c041c01252f2f0f450b323d2a2a564f100e5a202a4e5b4b1c2e3a1a153e4853454f3a1c510f565253555f3155264d58280a372c551b024a59772d5d7c5e5759321d4d341e36330c354f253f3f4b427d3f16122a0a1f5d520129275f264d1829234d060506583e1f2128094436060a353833354e2858336859275e571b3858364c1f1629037e01484677576c4614545c082737011426335b415d1b1a3801313a13502e060d0d3b0f500c3d343e5e5151053102261c05252f2f264c083b1f2607564f10237009784a0d4f1c2a19331c175157134b343451265f515a775f3555264d5b212f3e0e551f024a595e245e237c575d3c354d3717133a2e354b253f3f484b583634122e0a1f5d5108292e5b26491829234e0f0a0f7a3e1b2d012f473f0903213f3725480e5b3a67501559531b38581f45361f0b0f53241c565e5e7348365458082737281d393d5f41591b1a3828380f1a722d570d0d3b2659031230685a5f71221d28031527252b2f264c0b32102f03004b102753202a4a524b1c2e19331c3b65544c693a1851265f525352561755224373212c372b5c3d024e595e245d2a7c5e5932194d37171033213c69253b33616d5b3f3b1b1c0d1b5d51080f27722f7b1f2d234e0f2306753739212c056e3f200a183115354a285b3a4e592057711b3c581f451f162e0671274c465e5e5a41105d5c082337281d151e76485d4f1e3828380c137d34535909350e592a3d333778515d22180b001c022c092f224c0b323926005f69102353202a4a5b48150c19371c3e48574566331c51225f52535447185c004d5f2d05112855321b7c5e5a245d2a5a57703b2b4a331710330835662c193f4c47723f121203031f5d55080f275b26641129754a0f23065c3e1c280e09433f200a31381a3c4e7c5f3a4e59235e7e123b5f1b451f1628167e3e48125a5e5a4110545f0101372c1d10345f415e121a382c380c13542d50042b3b22592a3d303e785859221c0b001c01250626004c0f32392603566619015324291e6d4b1c0310051b3a4857454f3a355810585653515f31550f447d77283728551b02630b5e725924725759323b543741143308354a0f3c36614c5f3f12122a0a3654520f0b275b264d18002a4e2027065c3e1f212b004438240a313833354d215b3a4a59235e571b3b511f451b162d0f57276e4f5e5e5e4110545c080e3e0e1d14345f415d1b33310e380813542d530d243200592e3e64185e51703b2e0c041c01252f2f0f1e2d643d2557464f100e4a207c4e5b4b1c2a191a4e3e1c53454f3a19720f565154555f3155264d582805302c551b024e7a772d5e2d5e5759321d4d113810640c354f253a1b4b42583816122a0a1f5d52010f275f264d1829234d0623065830372128096136200a353833354b3d723368592752041b3858364c3916290f5727406177576c411457093e2737013226335b415d1b1a380e170c45502d530d0d1525500c3d343e5e5159223102031b05252f2f264c083b102107564f1027530923495c4f1c2a19331c174154424b3a1c51265f7b5a52583555264d5e022f3e2b521f024a595e245e235a575d321d4d3717363a08354b253f3f484b723634122e0a1f5d5108262e7d26491829234e0f0a0f7a3e1b212809473f0903073f37354e285b3a670b1559531b1c581f45361f2d0f53241c465e5e7348100058082737281d393d5c465915323828380f1a572a5703253b2659033430115a5f7122180b261502222b2f264c0e1f3a2f00514b102753202a4952481b2e19331c3e487e4c4f3a1851265f525377563103224d5b212c37015c3d024e595e245d2a735e7f32194d37171033213c69733b33616d5b3f3b1b1c0d1b5d51080f27722f7b1f2d234e0f2306756c1f772c09473f200a173130324a285b3a4b732057541c3c581f451f162e0654204c465e5e5a41395d5c272337281d103476485e1c1e3828380c135724500a093b26592a3d19375e515d22180b001c272c2f2f224c0b3239262a4f69462353202a4a5b62150c19371c3e48574566683a07225f5253515f184c001b5f227801285532507c5e5a245d2a5a57703b1d4a33171033083566773f6b4c4b5b3f121203031c5a55080f275b264e112a244a0f23065c3e36282b0e433f200a31381a3c4d2f5f3a4e59235e54123b5f1b451f162d0f7e2e48465a5e5a411054750127372c1d10345f4174341a6e2c380c1351357a042b3b22592a3d303e77587f221c0b001c01250900261a0f3e1010064d66191154242a4a5b4b1c0310231b3a5851634f3a1f58005f5653515f31550f1f5b75283728551b0249505d23592a5a57593234443410143308354f2516364b4c5f3f12122a0a1c54050f0b275b264d18002a4e5927065c3e1f210100473f240a3138342e68215b3a4a59235e571b115139451b162d0f5f00614f785e5e4110545c080e3e0e1d14370b415d1b33311e3f0813542d530d2432105e2e3d303e5e51702b180c041c01252f2f25452d323d2603564f100e5a232d4e5b4b1c2a191a153d4f53454f3a1c510f565154555f3155264d58282f302c551b024f40772d5d2a5e5759321d4d1e1e10330c354f253f3f6e425b3f16122a0a1f5d780129275f264d18292367060506583e1f2128096e36060a353b66034e287233785e275e571b3858366a2911290f5727484677576c4614545c082737011410345b415d1b12160e310c47502d530908220f50293a343e5e5159223102031b05252f2f2355083b3a2107564f10275309234a5b4f1c2a19331c174157454b3a1c51265f747c51083555264d5b21053e28551f024a595b3c74237c575d31494d3717393a2e354b266b3f484b726d24152e0a1f5d5108262e6d21491829234e0f0a0f6a391b212809473f0913313e37354e285b3a670b2308531b3858176b391f2d0f532748465e5e7348135358082737281d393d5c46591b1a382838251a542d570d0d3b26590334303e5a5159221f28263301732b2f264c0b321f2f03564b130965253363747d1a2e19331c3e487e5c693d18520b69525378560752224d5b212c37015c2d054e595e245d2a735e6f35194d37171033212c79233b3f484b5b3f3b1b2a0c1b5d75080f277d744d4c2d2f670f2b287a371f212c09473f2729183130324a285b3a4e590a57571b3c581f451f16040657274c465e5e5a41365d5c082337281d103476487b1b1e3b05120c137d24650b093b26592a3d192768575d22180b001c28771928224f5a2239262a0479172353202a4a5b6d152a19371f6a6e5f7566682a57225f5253515f184c264b5f212c372855320b7c5f5a24792a5a577f601519331b39362607692c3c384c4b5b3f121203031f5d55080f275c06641129234a0f23065c3e39282809433f200a31381a3c4e285f36676f016c7e490e5e1b451f162d0f7e757e405a5e5a411054750111312c1d10345f4174122c3f2c380c13542d7a042b3b2255032d303e77587f221c0b001c01250636104a0f323926035666092755242a4a5b4b1c0310151b3a4857454f3a3a03260b565f785f315500445826283728551b0263505e24592a5a5759323b443717143308354f2516366e4b5f333b242a0a3644670e0b275b264d18003a780927065c3e1f2101007139240a3138333567076d3d4a5a0e5e571b115139451b1a041f5727614f785e5e4110545c080e651e1b14345f415d1b3317283e0813542d530d2414105f2e3d303e5e51702b2e0c041f2c132f2f001e0b663d2a2a564f10015a202a4e5b4b1c2a1915153e4853454f3a1c5100565253555362552e6a72281a302c562a244a59770b6b2c5e5759321d4d1e0e26350c354f253f3f61426d3816117b1a1f5d785a39215f2a6408292367060506583e1f2128096e36060a353833354e287233785f275e571b3858366a1f10290f57274846770c6c4714545c082737011426335b42702d1a380e6a044750217a08230900502a3d343e5e5159223e02001c05252f2f0474223b1f2607551b26275309337c5d4f1c2a19331c175161434b3a1c51265f7b4a67593555264d5b21053e1e531f024a595e2474237c575d31337b371739613e334b253f3f484b726d24142e0a1f5d5108263e5b20491829234e0f0a1f4c381b312e09473f0903073e37354e285b3a67500559531b38581f4539392d59532b61465e5e73481054580827372d05393d79415917330e2838251a622b570d0d3b2659031206385a515922180b291537232b2f264c0b32103f35504b102753202a63747d1a2e19331c3e487e5c793c1851265f5253780d0753224d5b212c3701072d044e595e245d2a735e5934194d37171033211a79233b3f484b5b3f3b1b1c0c1b5d51080f27722f7b1f2d2063392306753739212c0a133f200a183105354a2b0e3a4e590a0c611d3c581f451f16041661214c465e5e5a41395d6a0e2337281d103476486b1d1e3828380c137d7f650b093b26592a3d192768575d22180b001c280a1929224c0b3239262a7979162353202a4a5b62052a1f371c3e48574566332a57225f5253515f185c104b5f212c372855321b7c5f5a245d2a5a57703b2b4a331441230835662c09384c48761912120303295a550b21015b2664111f254a0f23065c3e36381e0f433f200a31381a1a782e5f3a4e59235e7e020e5f1b464e162d0f7e087e405a5e5a411054750111312c1d10345f4174122c6e2c380c13542d7a220d3d22592a3d303e774859241c0b273a01250626104a0f323926035666091155242a4a5b4b1c0336051a3a4857454f3a3558105856507f6931550f446d27283728551b0263506822592a5a57593234440111143308354f2516267e4d5f3f12122a0a3644670e0b275b264d18000c780927065c3e1f21015b7139240a3138333567216d3c4a59235e571b11411f111b18050f57276e145e585e4110545c080e2e1e1b14347b675d1b33171e3e0813542d530d2422105f2e3d303e5e51703b2e0d041c01252f2f0f453d343d2603564f100e5a162c4e5b4b1c2a191a15184f5346612a1c510f567454555c1f55264d72731a312c551b024a59772d6b2c5e5759321d4d1e0e26350c354f253f3f61426d3916122a0a1f5d78010f215f264d18292368062306583e1f2128096e36200a353833354e287268785f275e733d3858366a2910290f5727484677476c4714545c082737014f26325b415d1b1a3801313a15502d530d0d3b0f501c3b3432776759223102264a05267a09264c22601f2107556200275309237c5d4f1c2a19331c176761434b3a1c51265f7b5a67593555264d5b21053e28531f024a595e247b235a575d321d4d3717133a08354b253f3f4f50723634122e0a1f5d5108262e6d20491801334e0f0a1f6a381b210c2f473f0925073e37354e285b3a67501558531b38581f45361f1b08532465465e5e7313360258082737281d392d79175917331e28382541622a570e231d2659032406385a515922180b291537732b2c726a0b321f2f03504b102753202a63524b1c2e19331c3e48714c4f3a185f0e5f525378563155224d5b212c37015c3d024e595e245d2a735e7f32194d37171033216779233b3f6f6d5b3f3b3d1c0c1b5d51080f27722f7b1e2d234e0f2306753739212c0a132f200a17173b634a285b3f1e7e0a57711b3c5b4a731f16040661214c465e5e5a4139066a5e2334191d103479485d1d1e3828380c137224530d093b26592a3d19375e515d22180b001c272c2f2f224c0b3239262a5f69102353202a4a5b62150c19371c3e48574566333a51225f5253515f1807104b5f2108112855322d7c5f5a245d2a5a57703b2b4a33143e330835662c193f4c47723f12120303395d550426115b2664111f244a0c0d205c3e36281e0f433f200a313815674e7c5f3a4e59235e711238581b451f162d0f712e48465a5e5a451774750127372c1d10345a5974121a382c380c13500e7a042b3b22592a3d303e77587f221c0b001c01250626004c0f323926035666421155242a4a5b4b1c034b051a3a4857454f3a355810585650004f31550f447d2128347d551b026350682359290b57593234440111143308354f2516367e4b5f3c3c122a0a3954515f0b275b264d180f2a4e0f27065c3e1f210e00473f240a313833356721583d4a59235e5203115139451b162d0f5727614f785e5e4110545c080e3e1e1d14345f415d1b33311e380810793d530d2469105f2e3d303e5e51703b2e0d041c01252f2f0f451b353d3605564f100e5a162d4e58663a2a191a15084f5346623a1c510f566455555f3155264d72730a612c5932024f55783d5d7c5e5759321d4d111e10330c354f253f3f61425838161c020a1f5d78010f7f5f264d182c3a6806235e583e1f212d126e36200a373833354e2872334e59275e571b3858364c1f16290c0611484677575a4714545c082737013210325b415d1b1a3801310c15502d530d0d3b0f502a3b343e5e5159223102001b05267e2f264c223b392107556210275306054a0d4f1003193605184157454b3a1c512e0d745a52583555264d5e39053e2b521f024a595e24", structure.mapData());
    }
}