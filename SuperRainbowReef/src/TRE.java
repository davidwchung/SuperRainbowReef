/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static javax.imageio.ImageIO.read;

public class TRE extends JPanel{

    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 960;
    private BufferedImage world;
    private Graphics2D buffer;
    private JFrame jf;
    private Katch katch;
    private Pop pop;
    private drawObject drawWalls;
    private drawObject drawBlocks0;
    private drawObject drawBlocks1;
    private drawObject drawBlocks2;
    private drawObject drawBlocks3;
    private drawObject drawBlocks4;
    private drawObject drawBlocks5;
    private drawObject drawBlocks6;
    private drawObject drawBigLegs;
    private drawObject drawLifePowerUp;
    private drawObject drawSpeedPowerUp;
    private Collision collision;
    private boolean gameOver;
    private BufferedImage lifeimg;
    private BufferedImage emptylifeimg;
    private BufferedImage gameover;
    private BufferedImage highscore;
    private ArrayList<Player> highscores = new ArrayList<>();
    private TileLayer layer;

    public static void main(String[] args) {

        Thread x;
        TRE trex = new TRE();

        // Level options
        String[] maps = { "Easy", "Medium", "Hard"};

        // Select a level
        JFrame frame = new JFrame("Input Dialog Example");
        String userInput = (String) JOptionPane.showInputDialog(frame,"Please select a difficulty:","Level Select", JOptionPane.QUESTION_MESSAGE, null, maps, maps[0]);

        // Loads in map based off user input
        if (userInput == "Easy") {

          trex.layer = new TileLayer(new int[][] {

                // 800 = Block0 - White
                // 801 = Block1 - Red
                // 802 = Block2 - Yellow
                // 803 = Block3 - Green
                // 804 = Block4 - Turquoise
                // 805 = Block5 - Blue
                // 806 = Block6 - Pink
                // 807 = Walls
                // 808 = Big Legs
                // 809 = Life PowerUp
                // 810 = Speed PowerUp

                {   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0},
                {   0,  33,  34,  35,  36,  37,  38,  39,  40,  41,  42,  43,  44,  45,  46,  47,  48,  49,  50,  51,  52,  53,  54,  55,  56,  57,  58,  59,  60,  61,  62,   0},
                {   0,  65,  66,  67,  68,  69,  70,  71,  72,  73,  74,  75,  76,  77,  78, 800,  80,  81,  82,  83,  84,  85,  86,  87,  88,  89,  90,  91,  92,  93,  94,   0},
                {   0,  97,  98,  99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 800, 110, 801, 112, 800, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126,   0},
                {   0, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 800, 140, 801, 142, 802, 144, 801, 146, 800, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158,   0},
                {   0, 161, 162, 163, 164, 165, 166, 167, 168, 800, 170, 801, 172, 802, 174, 803, 176, 802, 178, 801, 180, 800, 182, 183, 184, 185, 186, 187, 188, 189, 190,   0},
                {   0, 193, 194, 195, 196, 197, 198, 800, 200, 801, 202, 808, 204, 803, 206, 804, 208, 803, 210, 808, 212, 801, 214, 800, 216, 217, 218, 219, 220, 221, 222,   0},
                {   0, 225, 226, 227, 228, 800, 230, 801, 232, 802, 234, 235, 236, 804, 238, 805, 240, 804, 242, 243, 244, 802, 246, 801, 248, 800, 250, 251, 252, 253, 254,   0},
                {   0, 257, 258, 800, 260, 801, 262, 802, 264, 803, 266, 804, 268, 805, 270, 806, 272, 805, 274, 804, 276, 803, 278, 802, 280, 801, 282, 800, 284, 285, 286,   0},
                {   0, 289, 290, 291, 292, 800, 294, 801, 296, 802, 298, 803, 300, 804, 302, 805, 304, 804, 306, 803, 308, 802, 310, 801, 312, 800, 314, 315, 316, 317, 318,   0},
                {   0, 321, 322, 323, 324, 325, 326, 800, 328, 801, 330, 802, 332, 803, 334, 808, 336, 803, 338, 802, 340, 801, 342, 800, 344, 345, 346, 347, 348, 349, 350,   0},
                {   0, 353, 354, 355, 356, 357, 358, 359, 360, 800, 362, 801, 364, 802, 366, 367, 368, 802, 370, 801, 372, 800, 374, 375, 376, 377, 378, 379, 380, 381, 382,   0},
                {   0, 385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 800, 396, 801, 398, 802, 400, 801, 402, 800, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414,   0},
                {   0, 417, 418, 419, 420, 421, 422, 423, 424, 425, 426, 427, 428, 800, 430, 801, 432, 800, 434, 435, 436, 437, 438, 439, 440, 441, 442, 443, 444, 445, 446,   0},
                {   0, 449, 450, 451, 452, 453, 454, 455, 456, 457, 458, 459, 460, 461, 462, 800, 464, 465, 466, 467, 468, 469, 470, 471, 472, 473, 474, 475, 476, 477, 478,   0},
                {   0, 481, 482, 483, 484, 485, 486, 487, 488, 489, 490, 491, 492, 493, 494, 495, 496, 497, 498, 499, 500, 501, 502, 503, 504, 505, 506, 507, 508, 509, 510,   0},
                {   0, 513, 514, 515, 516, 517, 518, 519, 520, 521, 522, 523, 524, 525, 526, 527, 528, 529, 530, 531, 532, 533, 534, 535, 536, 537, 538, 539, 540, 541, 542,   0},
                {   0, 545, 546, 547, 548, 549, 550, 551, 552, 553, 554, 555, 556, 557, 558, 559, 560, 561, 562, 563, 564, 565, 566, 567, 568, 569, 570, 571, 572, 573, 574,   0},
                {   0, 577, 578, 579, 580, 581, 582, 583, 584, 585, 586, 587, 588, 589, 590, 591, 592, 593, 594, 595, 596, 597, 598, 599, 600, 601, 602, 603, 604, 605, 606,   0},
                {   0, 609, 610, 611, 612, 613, 614, 615, 616, 617, 618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 629, 630, 631, 632, 633, 634, 635, 636, 637, 638,   0},
                {   0, 641, 642, 643, 644, 645, 646, 647, 648, 649, 650, 651, 652, 653, 654, 655, 656, 657, 658, 659, 660, 661, 662, 663, 664, 665, 666, 667, 668, 669, 670,   0},
                {   0, 673, 674, 675, 676, 677, 678, 679, 680, 681, 682, 683, 684, 685, 686, 687, 688, 689, 690, 691, 692, 693, 694, 695, 696, 697, 698, 699, 700, 701, 702,   0},
                {   0, 705, 706, 707, 708, 709, 710, 711, 712, 713, 714, 715, 716, 717, 718, 719, 720, 721, 722, 723, 724, 725, 726, 727, 728, 729, 730, 731, 732, 733, 734,   0},
                {   0, 737, 738, 739, 740, 741, 742, 743, 744, 745, 746, 747, 748, 749, 750, 751, 752, 753, 754, 755, 756, 757, 758, 759, 760, 761, 762, 763, 764, 765, 766,   0}}
            );
        }

        // Loads in map based off user input
        else if (userInput == "Medium") {

            trex.layer = new TileLayer(new int[][] {

                // 800 = Block0 - White
                // 801 = Block1 - Red
                // 802 = Block2 - Yellow
                // 803 = Block3 - Green
                // 804 = Block4 - Turquoise
                // 805 = Block5 - Blue
                // 806 = Block6 - Pink
                // 807 = Walls
                // 808 = Big Legs
                // 809 = Life PowerUp
                // 810 = Speed PowerUp

                {   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0},
                {   0,  33, 808,  35,  36, 807, 807,  39,  40,  41,  42,  43, 808,  45,  46,  47,  48,  49, 808,  51,  52,  53,  54,  55,  56, 807, 807,  59, 808,  61,  62,   0},
                {   0,  65,  66,  67,  68, 807, 807,  71,  72,  73,  74,  75,  76,  77,  78,  79,  80,  81,  82,  83,  84,  85,  86,  87,  88, 807, 807,  91,  92,  93,  94,   0},
                {   0,  97,  98,  99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126,   0},
                {   0, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158,   0},
                {   0, 800, 162, 806, 164, 800, 166, 806, 168, 800, 170, 806, 172, 800, 174, 806, 176, 800, 178, 806, 180, 800, 182, 806, 184, 800, 186, 806, 188, 800, 190,   0},
                {   0, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222,   0},
                {   0, 801, 226, 803, 228, 801, 230, 803, 232, 801, 234, 803, 236, 801, 238, 803, 240, 801, 242, 803, 244, 801, 246, 803, 248, 801, 250, 803, 252, 801, 254,   0},
                {   0, 804, 258, 802, 260, 804, 262, 802, 264, 804, 266, 802, 268, 804, 270, 802, 272, 804, 274, 802, 276, 804, 278, 802, 280, 804, 282, 802, 284, 804, 286,   0},
                {   0, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318,   0},
                {   0, 800, 322, 801, 324, 800, 326, 809, 328, 800, 330, 810, 332, 800, 334, 801, 336, 800, 338, 810, 340, 800, 342, 809, 344, 800, 346, 801, 348, 800, 350,   0},
                {   0, 806, 354, 800, 356, 806, 358, 800, 360, 806, 362, 800, 364, 806, 366, 800, 368, 806, 370, 800, 372, 806, 374, 800, 376, 806, 378, 800, 380, 806, 382,   0},
                {   0, 385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414,   0},
                {   0, 417, 418, 419, 420, 421, 422, 423, 424, 425, 426, 427, 428, 429, 430, 431, 432, 433, 434, 435, 436, 437, 438, 439, 440, 441, 442, 443, 444, 445, 446,   0},
                {   0, 449, 450, 451, 452, 453, 454, 455, 456, 457, 458, 459, 460, 461, 462, 463, 464, 465, 466, 467, 468, 469, 470, 471, 472, 473, 474, 475, 476, 477, 478,   0},
                {   0, 481, 482, 483, 484, 485, 486, 487, 488, 489, 490, 491, 492, 493, 494, 495, 496, 497, 498, 499, 500, 501, 502, 503, 504, 505, 506, 507, 508, 509, 510,   0},
                {   0, 513, 514, 515, 516, 517, 518, 519, 520, 521, 522, 523, 524, 525, 526, 527, 528, 529, 530, 531, 532, 533, 534, 535, 536, 537, 538, 539, 540, 541, 542,   0},
                {   0, 545, 546, 547, 548, 549, 550, 551, 552, 553, 554, 555, 556, 557, 558, 559, 560, 561, 562, 563, 564, 565, 566, 567, 568, 569, 570, 571, 572, 573, 574,   0},
                {   0, 577, 578, 579, 580, 581, 582, 583, 584, 585, 586, 587, 588, 589, 590, 591, 592, 593, 594, 595, 596, 597, 598, 599, 600, 601, 602, 603, 604, 605, 606,   0},
                {   0, 609, 610, 611, 612, 613, 614, 615, 616, 617, 618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 629, 630, 631, 632, 633, 634, 635, 636, 637, 638,   0},
                {   0, 641, 642, 643, 644, 645, 646, 647, 648, 649, 650, 651, 652, 653, 654, 655, 656, 657, 658, 659, 660, 661, 662, 663, 664, 665, 666, 667, 668, 669, 670,   0},
                {   0, 673, 674, 675, 676, 677, 678, 679, 680, 681, 682, 683, 684, 685, 686, 687, 688, 689, 690, 691, 692, 693, 694, 695, 696, 697, 698, 699, 700, 701, 702,   0},
                {   0, 705, 706, 707, 708, 709, 710, 711, 712, 713, 714, 715, 716, 717, 718, 719, 720, 721, 722, 723, 724, 725, 726, 727, 728, 729, 730, 731, 732, 733, 734,   0},
                {   0, 737, 738, 739, 740, 741, 742, 743, 744, 745, 746, 747, 748, 749, 750, 751, 752, 753, 754, 755, 756, 757, 758, 759, 760, 761, 762, 763, 764, 765, 766,   0}}
            );
        }

        // Loads in map based off user input
        else if (userInput == "Hard") {

            trex.layer = new TileLayer(new int[][] {

                // 800 = Block0 - White
                // 801 = Block1 - Red
                // 802 = Block2 - Yellow
                // 803 = Block3 - Green
                // 804 = Block4 - Turquoise
                // 805 = Block5 - Blue
                // 806 = Block6 - Pink
                // 807 = Walls
                // 808 = Big Legs
                // 809 = Life PowerUp
                // 810 = Speed PowerUp

                {  0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0},
                {  0, 809,  34, 809,  36, 807, 807,  39, 808,  41,  42, 807, 807,  45,  46, 808,  48,  49,  50, 807, 807,  54, 808,  56,  60, 807, 807, 809,  60, 809,  62,   0},
                {  0, 806,  66, 805,  68, 807, 807,  71,  72,  73,  74, 807, 807,  77,  78,  79,  80,  81,  82, 807, 807,  87,  86,  87,  88, 807, 807, 805,  92, 806,  94,   0},
                {  0, 806,  98, 805, 100, 807, 807, 804, 104, 803, 106, 807, 807, 802, 110, 801, 112, 802, 114, 807, 807, 803, 118, 804, 120, 807, 807, 805, 124, 806, 126,   0},
                {  0, 806, 130, 805, 132, 807, 807, 804, 136, 803, 138, 807, 807, 802, 142, 801, 144, 802, 146, 807, 807, 803, 150, 804, 152, 807, 807, 805, 156, 806, 158,   0},
                {  0, 806, 162, 805, 164, 807, 807, 804, 168, 803, 170, 807, 807, 802, 174, 801, 176, 802, 178, 807, 807, 803, 182, 804, 184, 807, 807, 805, 188, 806, 190,   0},
                {  0, 810, 194, 810, 196, 807, 807, 804, 200, 803, 202, 807, 807, 802, 206, 801, 208, 802, 210, 807, 807, 803, 214, 804, 216, 807, 807, 810, 220, 810, 222,   0},
                {  0, 806, 226, 805, 228, 807, 807, 804, 232, 803, 234, 807, 807, 802, 238, 801, 240, 802, 242, 807, 807, 803, 246, 804, 248, 807, 807, 805, 252, 806, 254,   0},
                {  0, 806, 258, 805, 260, 807, 807, 804, 264, 803, 266, 807, 807, 802, 270, 801, 272, 802, 274, 807, 807, 803, 278, 804, 280, 807, 807, 805, 284, 806, 286,   0},
                {  0, 289, 290, 291, 292, 807, 807, 295, 296, 297, 298, 807, 807, 301, 302, 303, 304, 305, 306, 807, 807, 309, 310, 311, 312, 807, 807, 315, 316, 317, 318,   0},
                {  0, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341, 342, 343, 344, 345, 346, 347, 348, 349, 350,   0},
                {  0, 353, 354, 355, 356, 357, 358, 359, 360, 361, 362, 363, 364, 365, 366, 367, 368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 380, 381, 382,   0},
                {  0, 385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414,   0},
                {  0, 417, 418, 419, 420, 421, 422, 423, 424, 425, 426, 427, 428, 429, 430, 431, 432, 433, 434, 435, 436, 437, 438, 439, 440, 441, 442, 443, 444, 445, 446,   0},
                {  0, 449, 450, 451, 452, 453, 454, 455, 456, 457, 458, 459, 460, 461, 462, 463, 464, 465, 466, 467, 468, 469, 470, 471, 472, 473, 474, 475, 476, 477, 478,   0},
                {  0, 481, 482, 483, 484, 485, 486, 487, 488, 489, 490, 491, 492, 493, 494, 495, 496, 497, 498, 499, 500, 501, 502, 503, 504, 505, 506, 507, 508, 509, 510,   0},
                {  0, 513, 514, 515, 516, 517, 518, 519, 520, 521, 522, 523, 524, 525, 526, 527, 528, 529, 530, 531, 532, 533, 534, 535, 536, 537, 538, 539, 540, 541, 542,   0},
                {  0, 545, 546, 547, 548, 549, 550, 551, 552, 553, 554, 555, 556, 557, 558, 559, 560, 561, 562, 563, 564, 565, 566, 567, 568, 569, 570, 571, 572, 573, 574,   0},
                {  0, 577, 578, 579, 580, 581, 582, 583, 584, 585, 586, 587, 588, 589, 590, 591, 592, 593, 594, 595, 596, 597, 598, 599, 600, 601, 602, 603, 604, 605, 606,   0},
                {  0, 609, 610, 611, 612, 613, 614, 615, 616, 617, 618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 629, 630, 631, 632, 633, 634, 635, 636, 637, 638,   0},
                {  0, 641, 642, 643, 644, 645, 646, 647, 648, 649, 650, 651, 652, 653, 654, 655, 656, 657, 658, 659, 660, 661, 662, 663, 664, 665, 666, 667, 668, 669, 670,   0},
                {  0, 673, 674, 675, 676, 677, 678, 679, 680, 681, 682, 683, 684, 685, 686, 687, 688, 689, 690, 691, 692, 693, 694, 695, 696, 697, 698, 699, 700, 701, 702,   0},
                {  0, 705, 706, 707, 708, 709, 710, 711, 712, 713, 714, 715, 716, 717, 718, 719, 720, 721, 722, 723, 724, 725, 726, 727, 728, 729, 730, 731, 732, 733, 734,   0},
                {  0, 737, 738, 739, 740, 741, 742, 743, 744, 745, 746, 747, 748, 749, 750, 751, 752, 753, 754, 755, 756, 757, 758, 759, 760, 761, 762, 763, 764, 765, 766,   0}}
            );
        }

        trex.init();

        try {
            while (!trex.gameOver) {

                // Checks you still have lives/big legs to kill remaining
                if (trex.pop.getStatus() || trex.collision.gameOver()) {
                    trex.gameOver = true;
                }

                // Update Position of Katch and Pop
                trex.katch.update();
                trex.pop.update();

                // Collision Checking
                trex.collision.KatchVsPop(trex.katch,trex.pop);
                trex.collision.BlocksVsPop(trex.pop);
                trex.collision.WallsVsPop(trex.pop);
                trex.collision.BigLegVsPop(trex.pop);
                trex.collision.LifePowerUpVsPop(trex.pop);
                trex.collision.SpeedPowerUpVsPop(trex.pop);

                // Sets title to score
                trex.jf.setTitle("Score: " + trex.collision.getScore());

                // Draw everything
                trex.repaint();

                Thread.sleep(1000 / 144);
            }
        } catch (InterruptedException ignored) {

        }
    }

    public void init() {

        this.jf = new JFrame("Super Rainbow Reef");
        this.world = new BufferedImage(TRE.SCREEN_WIDTH, TRE.SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
        gameOver = false;

        BufferedImage katchimg = null, popimg = null, block0img = null, block1img = null, block2img = null, block3img = null, block4img = null, block5img = null, block6img = null,
                wallimg = null, biglegimg = null, lifepowerupimg = null, speedpowerupimg = null;

        try {

//            System.out.println(System.getProperty("user.dir"));
            /*
             * note class loaders read files from the out folder (build folder in netbeans) and not the
             * current working directory.
             */

            // Use to pull in resources when running locally
//            katchimg = read(new File("Insert File Location of: Katch.gif"));
//            popimg = read(new File("Insert File Location of: Pop.png"));
//            block0img = read(new File("Insert File Location of: Block0.gif"));
//            block1img = read(new File("Insert File Location of: Block1.gif"));
//            block2img = read(new File("Insert File Location of: Block2.gif"));
//            block3img = read(new File("Insert File Location of: Block3.gif"));
//            block4img = read(new File("Insert File Location of: Block4.gif"));
//            block5img = read(new File("Insert File Location of: Block5.gif"));
//            block6img = read(new File("Insert File Location of: Block6.gif"));
//            wallimg = read(new File("Insert File Location of: Wall.png"));
//            biglegimg = read(new File("Insert File Location of: BigLeg.png"));
//            lifepowerupimg = read(new File("Insert File Location of: LifePowerUp.png"));
//            speedpowerupimg = read(new File("Insert File Location of: SpeedPowerUp.png"));
//            lifeimg = ImageIO.read(new File("Insert File Location of: Life.png"));
//            emptylifeimg = ImageIO.read(new File("Insert File Location of: EmptyLife.png"));
//            gameover = ImageIO.read(new File("Insert File Location of: GameOver.jpg"));
//            highscore = ImageIO.read(new File("Insert File Location of: HighScore.png"));

            // Use to pull in resources when running jar
//            t1img = read(new File("resources\\tank1.png"));
//            t2img = read(new File("resources\\tank1.png"));
//            pimg = read(new File("resources\\Life.png"));
//            gameover = ImageIO.read(new File("resources\\GameOver.jpg"));
//            wallimg = ImageIO.read(new File("resources\\DestructibleWallCropped.png"));

            katchimg = read(new File("resources\\Katch.gif"));
            popimg = read(new File("resources\\Pop.png"));
            block0img = read(new File("resources\\Block0.gif"));
            block1img = read(new File("resources\\Block1.gif"));
            block2img = read(new File("resources\\Block2.gif"));
            block3img = read(new File("resources\\Block3.gif"));
            block4img = read(new File("resources\\Block4.gif"));
            block5img = read(new File("resources\\Block5.gif"));
            block6img = read(new File("resources\\Block6.gif"));
            wallimg = read(new File("resources\\Wall.png"));
            biglegimg = read(new File("resources\\BigLeg.png"));
            lifepowerupimg = read(new File("resources\\LifePowerUp.png"));
            speedpowerupimg = read(new File("resources\\SpeedPowerUp.png"));
            lifeimg = ImageIO.read(new File("resources\\Life.png"));
            emptylifeimg = ImageIO.read(new File("resources\\EmptyLife.png"));
            gameover = ImageIO.read(new File("resources\\GameOver.jpg"));
            highscore = ImageIO.read(new File("resources\\HighScore.png"));


        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        // Create Pop and Katch instance
        pop = new Pop(623, 865, 0, 0, 315, popimg);
        katch = new Katch(600, 905, katchimg, pop);
        KatchControl kc = new KatchControl(katch, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE, KeyEvent.VK_ENTER);

        // Create Collision object
        collision = new Collision(layer);

        // Objects we will use to draw blocks/walls/big legs/powerups
        drawBlocks0 = new drawObject(block0img, collision.getBlocks0());
        drawBlocks1 = new drawObject(block1img, collision.getBlocks1());
        drawBlocks2 = new drawObject(block2img, collision.getBlocks2());
        drawBlocks3 = new drawObject(block3img, collision.getBlocks3());
        drawBlocks4 = new drawObject(block4img, collision.getBlocks4());
        drawBlocks5 = new drawObject(block5img, collision.getBlocks5());
        drawBlocks6 = new drawObject(block6img, collision.getBlocks6());
        drawWalls = new drawObject(wallimg, collision.getWalls());
        drawBigLegs = new drawObject(biglegimg, collision.getBiglegs());
        drawLifePowerUp = new drawObject(lifepowerupimg, collision.getLifePowerUp());
        drawSpeedPowerUp = new drawObject(speedpowerupimg, collision.getSpeedPowerUp());

        // Fill arraylist with high scores
        highscores.add(new Player("AAA", 3500));
        highscores.add(new Player("BBB", 3000));
        highscores.add(new Player("CCC", 2500));
        highscores.add(new Player("DDD", 2000));
        highscores.add(new Player("EEE", 1500));
        highscores.add(new Player("FFF", 1000));
        highscores.add(new Player("GGG", 750));
        highscores.add(new Player("HHH", 500));
        highscores.add(new Player("III", 250));

        this.jf.setLayout(new BorderLayout());
        this.jf.add(this);

        this.jf.addKeyListener(kc);

        this.jf.setSize(TRE.SCREEN_WIDTH, TRE.SCREEN_HEIGHT + 30);
        this.jf.setResizable(false);
        jf.setLocationRelativeTo(null);

        this.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jf.setVisible(true);

    }

    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        buffer = world.createGraphics();

        super.paintComponent(g2);

        if (!gameOver) {

            // Draw Background
            layer.DrawLayer(buffer);

            // Draw blocks/walls/big legs/powerups
            this.drawBlocks0.drawImage(buffer);
            this.drawBlocks1.drawImage(buffer);
            this.drawBlocks2.drawImage(buffer);
            this.drawBlocks3.drawImage(buffer);
            this.drawBlocks4.drawImage(buffer);
            this.drawBlocks5.drawImage(buffer);
            this.drawBlocks6.drawImage(buffer);
            this.drawWalls.drawImage(buffer);
            this.drawBigLegs.drawImage(buffer);
            this.drawLifePowerUp.drawImage(buffer);
            this.drawSpeedPowerUp.drawImage(buffer);

            // Drawing Katch/Pop
            this.katch.drawImage(buffer);
            this.pop.drawImage(buffer);

            g2.drawImage(world,0,0,null);

            g2.setColor(Color.BLACK);
            g2.setFont(new Font("TimesRoman", Font.BOLD, 24));
            DecimalFormat df = new DecimalFormat("0.0##");
            String speed = df.format(this.pop.getSpeed());
            g2.drawString("Speed: " + speed, 45, 927);
            g2.drawString("Slow Downs: " + this.pop.getNumberOfSpeedPowerUps(), 45, 952);

            // Outline for empty hearts (always 3)
            for (int i = 0; i < 3; i++) {
                g2.drawImage(emptylifeimg, 45 + (i * 50), 860, null);
            }

            // Draw hearts remaining
            for (int i = 0; i < this.pop.getLives(); i++) {
                g2.drawImage(lifeimg, 45 + (i * 50), 860, null);
            }

        }

        // Game Over Screen,
        else {

            // Game Over and High Score Image
            g2.setColor(Color.black);
            g2.fillRect(0,0,1280,960);
            g2.drawImage(gameover, 220,50, null);
            g2.drawImage(highscore, 340, 175, null);

            // Write high scores to screen
            g2.setColor(Color.yellow);
            g2.setFont(new Font("Haettenschweiler", Font.BOLD, 40));

            // Sort high scores after user's score is added
            highscores.add(new Player("YOU", collision.getScore()));
            Collections.sort(highscores, new Sortbyroll());

            // Print arraylist of high scores
            for (int i = 0; i < 10; i++) {
                g2.drawString(i+1 + ".", 400, 325 + i * 60);
                g2.drawString(highscores.get(i).getPlayerName(), 600, 325 + i * 60);
                g2.drawString(Integer.toString(highscores.get(i).getPlayerScore()), 800, 325 + i * 60);
            }

        }

    }

}
