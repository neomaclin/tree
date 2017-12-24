package org.qq.question

import org.scalatest.FunSuite

class PackageSuite extends FunSuite {

  //please refer to https://github.com/sbt/sbt/pull/274
  //for the expected branching behaviours

  test("case 1") {
    val generated =
      asciiDisplay(TreeNode("Root",
        children = List(TreeNode("level1-1"),
          TreeNode("level1-2"),
          TreeNode("level1-3"))))

    val expected =
      List("+-Root",
           "  +-level1-1",
           "  +-level1-2",
           "  +-level1-3")
    generated.foreach(println)
    assert(generated == expected)
  }

  test("case 2") {
    val generated =
      asciiDisplay(TreeNode("Root",
        children = List(
        TreeNode("level1-1",
          children = TreeNode("level2-1",
            children = TreeNode("level3-1") :: Nil) :: Nil),
        TreeNode("level1-2"),
        TreeNode("level1-3"))))

    val expected =
      List( "+-Root",
            "  +-level1-1",
            "  | +-level2-1",
            "  |   +-level3-1",
            "  |",
            "  +-level1-2",
            "  +-level1-3")
    generated.foreach(println)
    assert(generated == expected)
  }

  test("case 3") {
    val generated =
      asciiDisplay(TreeNode("Root",
        children = List(
          TreeNode("level1-1",
            children = List(
              TreeNode("level2-1", children = TreeNode("level3-1") :: Nil),
              TreeNode("level2-2"))),
          TreeNode("level1-2"),
          TreeNode("level1-3"))))

    val expected =
      List( "+-Root",
        "  +-level1-1",
        "  | +-level2-1",
        "  | | +-level3-1",
        "  | |",
        "  | +-level2-2",
        "  |",
        "  +-level1-2",
        "  +-level1-3")
    generated.foreach(println)
    assert(generated == expected)
  }

  test("case 4") {
    val generated =
      asciiDisplay(TreeNode("Root",
        children = List(
          TreeNode("level1-1",
            children = List(
              TreeNode("level2-1",
                children = TreeNode("level3-1",
                  children = TreeNode("level4-1") :: TreeNode("level4-2") :: Nil) :: Nil),
              TreeNode("level2-2"))),
          TreeNode("level1-2"),
          TreeNode("level1-3"))))

    val expected =
      List( "+-Root",
        "  +-level1-1",
        "  | +-level2-1",
        "  | | +-level3-1",
        "  | |   +-level4-1",
        "  | |   +-level4-2",
        "  | |",
        "  | +-level2-2",
        "  |",
        "  +-level1-2",
        "  +-level1-3")
    generated.foreach(println)
    assert(generated == expected)
  }

  test("case 5") {
    val generated =
      asciiDisplay(TreeNode("Root",
        children = List(
          TreeNode("level1-1",
            children = List(
              TreeNode("level2-1",
              children = List(
                TreeNode("level3-1",
                  children = List(TreeNode("level4-1"), TreeNode("level4-2"))),
                TreeNode("level3-2"))),
              TreeNode("level2-2"), TreeNode("level2-3"))),
          TreeNode("level1-2"),
          TreeNode("level1-3"))))

    val expected =
      List( "+-Root",
        "  +-level1-1",
        "  | +-level2-1",
        "  | | +-level3-1",
        "  | | | +-level4-1",
        "  | | | +-level4-2",
        "  | | |",
        "  | | +-level3-2",
        "  | |",
        "  | +-level2-2",
        "  | +-level2-3",
        "  |",
        "  +-level1-2",
        "  +-level1-3")
    generated.foreach(println)
    assert(generated == expected)
  }


}