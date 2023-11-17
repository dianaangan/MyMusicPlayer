
package musicplayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author DianaGwapa
 */

public class MusicApp extends javax.swing.JFrame {
    
    private Map<Integer, String> lyricsWithTimestamps;
    private Timer timer;
    private AudioInputStream audioInputStream;
    private Clip clip;
    private boolean isPlaying = false;
    private boolean musicRepeat = false;
    private String playIconPath = "/musicplayer/Pictures/play.png";
    private String pauseIconPath = "/musicplayer/Pictures/pause.png";
    int previousVolume;
    private int currentTimestamp;
    private long totalMicroseconds;
    private long currentMicroseconds;
    private boolean resetClicked = false;
    private int positionInSeconds; 
    private boolean isMusicFinished = false;
    
    public MusicApp() {
        initComponents();
        
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        resetButton.setEnabled(false);
        
        volumeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int volume = volumeSlider.getValue();
                setVolume(volume);
                
                
                if (volume == 0) {
                    audioButton.setIcon(new ImageIcon(getClass().getResource("/musicplayer/Pictures/Mute.png")));
                } else {
                    audioButton.setIcon(new ImageIcon(getClass().getResource("/musicplayer/Pictures/OnAudio.png")));
                }
                
                audioButton.setFocusPainted(false);
                audioButton.setBorderPainted(false);
                audioButton.setContentAreaFilled(false);
            }
        });
        
        initializeLyricsWithTimestamps();

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clip != null && clip.isRunning()) {
                    positionInSeconds = (int) (clip.getMicrosecondPosition() / 1_000_000);
                    updateLyricsLabel(positionInSeconds);

                    updateMusicTimer(clip.getMicrosecondLength(), clip.getMicrosecondPosition());

                    if (positionInSeconds >= clip.getMicrosecondLength() / 1_000_000) {
                        centerButton.setIcon(new ImageIcon(getClass().getResource(playIconPath)));
                        isMusicFinished = true; 
                        
                    }
                }
            }
        });

         timer.start();
         
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        musicTimer = new javax.swing.JLabel();
        centerButton = new javax.swing.JButton();
        audioButton = new javax.swing.JButton();
        fastforwardButton = new javax.swing.JButton();
        rewindButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        volumeSlider = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        lyricsShow = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Music Player");
        setPreferredSize(new java.awt.Dimension(500, 650));
        setSize(new java.awt.Dimension(500, 650));
        getContentPane().setLayout(null);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setPreferredSize(new java.awt.Dimension(500, 650));
        jPanel2.setLayout(null);

        musicTimer.setBackground(new java.awt.Color(255, 255, 255));
        musicTimer.setFont(new java.awt.Font("Muna", 1, 20)); // NOI18N
        musicTimer.setForeground(new java.awt.Color(255, 255, 255));
        musicTimer.setText("03:03 ");
        jPanel2.add(musicTimer);
        musicTimer.setBounds(330, 380, 140, 50);

        centerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musicplayer/Pictures/play.png"))); // NOI18N
        centerButton.setBorder(null);
        centerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                centerButtonActionPerformed(evt);
            }
        });
        jPanel2.add(centerButton);
        centerButton.setBounds(190, 500, 60, 50);

        audioButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musicplayer/Pictures/OnAudio.png"))); // NOI18N
        audioButton.setBorder(null);
        audioButton.setDoubleBuffered(true);
        audioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                audioButtonActionPerformed(evt);
            }
        });
        jPanel2.add(audioButton);
        audioButton.setBounds(310, 500, 60, 50);

        fastforwardButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musicplayer/Pictures/fastforward.png"))); // NOI18N
        fastforwardButton.setBorder(null);
        fastforwardButton.setDoubleBuffered(true);
        fastforwardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fastforwardButtonActionPerformed(evt);
            }
        });
        jPanel2.add(fastforwardButton);
        fastforwardButton.setBounds(250, 500, 60, 50);

        rewindButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musicplayer/Pictures/rewind.png"))); // NOI18N
        rewindButton.setBorder(null);
        rewindButton.setHideActionText(true);
        rewindButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rewindButtonActionPerformed(evt);
            }
        });
        jPanel2.add(rewindButton);
        rewindButton.setBounds(120, 500, 70, 50);

        resetButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musicplayer/Pictures/reset.png"))); // NOI18N
        resetButton.setBorder(null);
        resetButton.setHideActionText(true);
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });
        jPanel2.add(resetButton);
        resetButton.setBounds(80, 500, 50, 50);

        volumeSlider.setBackground(new java.awt.Color(255, 255, 255));
        volumeSlider.setForeground(new java.awt.Color(51, 51, 51));
        volumeSlider.setDoubleBuffered(true);
        jPanel2.add(volumeSlider);
        volumeSlider.setBounds(360, 500, 100, 50);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/musicplayer/Pictures/jkjk.png"))); // NOI18N
        jPanel2.add(jLabel1);
        jLabel1.setBounds(120, 60, 250, 340);

        lyricsShow.setBackground(new java.awt.Color(255, 255, 255));
        lyricsShow.setFont(new java.awt.Font("Muna", 1, 18)); // NOI18N
        lyricsShow.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lyricsShow);
        lyricsShow.setBounds(120, 410, 250, 90);

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Muna", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Jungkookie");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(300, 30, 90, 50);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 0, 500, 650);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
                                           
        stopMusic(); 
        clip.close();

        try {
            audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("/musicplayer/Musics/Seven.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            resetButton.setEnabled(false); 
            isPlaying = false; 
            updatePlayPauseButtonIcon(); 
            
            currentTimestamp = 0;
            updateLyricsLabel(0);
            updateMusicTimer(clip.getMicrosecondLength(), 0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }//GEN-LAST:event_resetButtonActionPerformed
            
    private void rewindButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rewindButtonActionPerformed

        if (clip != null && clip.isRunning()) {
            int currentFrame = clip.getFramePosition();

            int newFrame = currentFrame - (int) (clip.getFormat().getFrameRate() * 5);

            clip.setFramePosition(Math.max(newFrame, 0));
        }
    }//GEN-LAST:event_rewindButtonActionPerformed

    private void fastforwardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fastforwardButtonActionPerformed

        if (clip != null && clip.isRunning()) {
            int currentFrame = clip.getFramePosition();

            int newFrame = currentFrame + (int) (clip.getFormat().getFrameRate() * 5);

            int maxFrames = clip.getFrameLength();
            clip.setFramePosition(Math.min(newFrame, maxFrames));
        }
    }//GEN-LAST:event_fastforwardButtonActionPerformed

    private void audioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_audioButtonActionPerformed

        // Audio button mute and unmute switch logic
        int currentVolume = volumeSlider.getValue();

        if (audioButton.getIcon().toString().equals(new ImageIcon(getClass().getResource("/musicplayer/Pictures/OnAudio.png")).toString())) {
            audioButton.setIcon(new ImageIcon(getClass().getResource("/musicplayer/Pictures/Mute.png")));
            previousVolume = volumeSlider.getValue();
            setVolume(0);
            volumeSlider.setValue(0);
        } else {
            audioButton.setIcon(new ImageIcon(getClass().getResource("/musicplayer/Pictures/OnAudio.png")));
            setVolume(previousVolume);
            volumeSlider.setValue(previousVolume);

        }

        audioButton.setFocusPainted(false);
        audioButton.setBorderPainted(false);
        audioButton.setContentAreaFilled(false);

    }//GEN-LAST:event_audioButtonActionPerformed

    private void centerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_centerButtonActionPerformed

     if (isMusicFinished) {
         
        isMusicFinished = false;

        currentTimestamp = 0;
        updateLyricsLabel(0);
        updateMusicTimer(clip.getMicrosecondLength(), 0);

        stopMusic();
        clip.close();
        try {
            audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("/musicplayer/Musics/Seven.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Play the music again
        playMusic();
        resetButton.setEnabled(true);
        addLineListener();
    } else {
        // If the music is currently playing, pause it
        if (!isPlaying || !clip.isRunning()) {
            playMusic();
            resetButton.setEnabled(true);
            addLineListener();
        } else {
            pauseMusic();
        }
    }
    updatePlayPauseButtonIcon();

    isMusicFinished = false;

    centerButton.setFocusPainted(false);
    centerButton.setBorderPainted(false);
    centerButton.setContentAreaFilled(false);
    }//GEN-LAST:event_centerButtonActionPerformed
    
     private void updatePlayPauseButtonIcon() {
        if (isPlaying) {
            centerButton.setIcon(new ImageIcon(getClass().getResource(pauseIconPath)));
        } else {
            centerButton.setIcon(new ImageIcon(getClass().getResource(playIconPath)));
        }
    }
    
    private void addLineListener() {
        if (clip != null) {
            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP && isPlaying) {
                        if (!musicRepeat) {
                            isPlaying = false;
                            isMusicFinished = true;
                            updatePlayPauseButtonIcon();
                        } else {
                            musicRepeat = false;
                        }
                    }
                }
            });
        }
    }

    
    private void pauseMusic() {
           if (clip != null && clip.isRunning()) {
               clip.stop();
               isPlaying = false;
           }
    }
       
    private void playMusic() {
        try {
            if (clip == null || musicRepeat) {
                audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("/musicplayer/Musics/Seven.wav"));
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                musicRepeat = false;  
            }

            isPlaying = true;
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

   private void stopMusic() {
        if (clip != null) {
            clip.stop();
            clip.close();
            isPlaying = false;
        }
    }

    
    private void setVolume(int volume) {
          if (clip != null) {
              FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
              float dB = (float) (Math.log(volume / 100.0) / Math.log(10.0) * 20.0);
              gainControl.setValue(dB);
          }
      }

    private void initializeLyricsWithTimestamps() {
        // Music Lyrics and Timestamps
        lyricsWithTimestamps = new HashMap<>();
        lyricsWithTimestamps.put(7, " "); 
        lyricsWithTimestamps.put(8, "Weight of the world on your shoulders");
        lyricsWithTimestamps.put(13, "I kiss your waist and ease your mind");
        lyricsWithTimestamps.put(16, "I must be favored to know ya ");
        lyricsWithTimestamps.put(20, "I take my hands and trace your lines ");
        lyricsWithTimestamps.put(22, "It's the way that we can ride ");
        lyricsWithTimestamps.put(24, "It's the way that we can ride (oh-oh, oh-oh)");
        lyricsWithTimestamps.put(27, "Think I met you in another life");
        lyricsWithTimestamps.put(28, "So break me off another time (oh-oh, oh-oh)");
        lyricsWithTimestamps.put(30, "You wrap around me and you give me life ");
        lyricsWithTimestamps.put(33, "And that's why night after night ");
        lyricsWithTimestamps.put(36, "I'll be lovin' you right ");
        lyricsWithTimestamps.put(38, "Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday (a week) ");
        lyricsWithTimestamps.put(42, "Monday, Tuesday, Wednesday, Thursday, Friday ");
        lyricsWithTimestamps.put(44, "Seven days a week ");
        lyricsWithTimestamps.put(46, "Every hour, every minute, every second ");
        lyricsWithTimestamps.put(48, "You know night after night");
        lyricsWithTimestamps.put(50, "I'll be lovin' you right, seven days a week ");
        lyricsWithTimestamps.put(55, "You love when I jump right in");
        lyricsWithTimestamps.put(57, "All of me I'm offering");
        lyricsWithTimestamps.put(59, "Show you what devotion is ");
        lyricsWithTimestamps.put(61, "Deeper than the ocean is ");
        lyricsWithTimestamps.put(63, "Wind it back, I'll take it slow ");
        lyricsWithTimestamps.put(64, "Leave you with that afterglow ");
        lyricsWithTimestamps.put(66, "Show you what devotion is ");
        lyricsWithTimestamps.put(69, "Deeper than the ocean is ");
        lyricsWithTimestamps.put(70, "It's the way that we can ride ");
        lyricsWithTimestamps.put(71, "It's the way that we can ride (oh-oh, oh-oh) ");
        lyricsWithTimestamps.put(74, "Think I met you in another life ");
        lyricsWithTimestamps.put(76, "So break me off another time (oh-oh, oh-oh) ");
        lyricsWithTimestamps.put(78, "You wrap around me and you give me life ");
        lyricsWithTimestamps.put(81, "And that's why night after night ");
        lyricsWithTimestamps.put(84, "I'll be lovin' you right, oh-oh-oh-oh ");
        lyricsWithTimestamps.put(86, "Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday (a week) ");
        lyricsWithTimestamps.put(90, "Monday, Tuesday, Wednesday, Thursday, Friday ");
        lyricsWithTimestamps.put(92, "Seven days a week ");
        lyricsWithTimestamps.put(94, "Every hour, every minute, every second ");
        lyricsWithTimestamps.put(97, "You know, night after night ");
        lyricsWithTimestamps.put(99, "I'll be fuckin' you right, seven days a week (yeah) ");
        lyricsWithTimestamps.put(101, "Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday (a week) ");
        lyricsWithTimestamps.put(105, "Monday, Tuesday, Wednesday, Thursday, Friday (oh-oh) ");
        lyricsWithTimestamps.put(107, "Seven days a week ");
        lyricsWithTimestamps.put(109, "Every hour, every minute, every second ");
        lyricsWithTimestamps.put(113, "You know, night after night ");
        lyricsWithTimestamps.put(114, "I'll be lovin' you right (Big Latto), seven days a week ");
        lyricsWithTimestamps.put(117, "Tightly take control, tightly take his soul ");
        lyricsWithTimestamps.put(118, "Take your phone and put it in the camera roll (uh) ");
        lyricsWithTimestamps.put(121, "Leave them clothes at the door ");
        lyricsWithTimestamps.put(122, "What you waitin' for? Better come and hit ya goals ");
        lyricsWithTimestamps.put(124, "Uh, he jump in it, both feet ");
        lyricsWithTimestamps.put(126, "Going 'til the sun up, we ain't getting no sleep ");
        lyricsWithTimestamps.put(128, "Seven days a week, seven different sheets ");
        lyricsWithTimestamps.put(130, "Seven different angles, I can be your fantasy ");
        lyricsWithTimestamps.put(132, "Open up say, Ah ");
        lyricsWithTimestamps.put(134, "Come here, baby, let me swallow your pride ");
        lyricsWithTimestamps.put(136, "What you on? I can match your vibe ");
        lyricsWithTimestamps.put(137, "Hit me up and I'ma Cha Cha Slide ");
        lyricsWithTimestamps.put(140, "You make Mondays feel like weekends ");
        lyricsWithTimestamps.put(142, "I make him never think about cheatin' (uh) ");
        lyricsWithTimestamps.put(144, "Got you skipping work and meetings, yeah (seven days a week, ooh) ");
        lyricsWithTimestamps.put(148, "Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday (a week) ");
        lyricsWithTimestamps.put(152, "Monday, Tuesday, Wednesday, Thursday, Friday ");
        lyricsWithTimestamps.put(154, "Seven days a week ");
        lyricsWithTimestamps.put(155, "Every hour, every minute, every second (oh, oh, oh) ");
        lyricsWithTimestamps.put(159, "You know, night after night ");
        lyricsWithTimestamps.put(161, "I'll be fuckin' you right, seven days a week (yeah) ");
        lyricsWithTimestamps.put(163, "Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday (a week) ");
        lyricsWithTimestamps.put(167, "Monday, Tuesday, Wednesday, Thursday, Friday ");
        lyricsWithTimestamps.put(170, "Seven days a week ");
        lyricsWithTimestamps.put(171, "Every hour, every minute, every second ");
        lyricsWithTimestamps.put(173, "You know, night after night ");
        lyricsWithTimestamps.put(176, "I'll be lovin' you right, seven days a week ");
        lyricsWithTimestamps.put(180, " ");

    }
    
    private void updateMusicTimer(long totalMicroseconds, long currentMicroseconds) {
        long totalSeconds = totalMicroseconds / 1_000_000;
        long currentSeconds = currentMicroseconds / 1_000_000;

        long minutesTotal = totalSeconds / 60;
        long secondsTotal = totalSeconds % 60;

        long minutesCurrent = currentSeconds / 60;
        long secondsCurrent = currentSeconds % 60;

        String timerText = String.format("%02d:%02d",
                minutesCurrent, secondsCurrent, minutesTotal, secondsTotal);

        musicTimer.setText(timerText);
    }

    private void updateLyricsLabel(int positionInSeconds) {

    String lyrics = findLyricsForPosition(positionInSeconds);

    if (!lyrics.equals(lyricsShow.getText())) {
        SwingUtilities.invokeLater(() -> {
            lyricsShow.setText("<html>" + lyrics + "</html>");
        });

        currentTimestamp = positionInSeconds;
    }
}

    private void showLyrics() {
        String lyrics = findLyricsForPosition(currentTimestamp);
            SwingUtilities.invokeLater(() -> {
                lyricsShow.setText("<html>" + lyrics + "</html>");
                lyricsShow.setVisible(true);;
            });
    }
    
    private String findLyricsForPosition(int positionInSeconds) {
        
        int closestTimestamp = lyricsWithTimestamps.keySet().stream()
                .min((t1, t2) -> Integer.compare(Math.abs(t1 - positionInSeconds), Math.abs(t2 - positionInSeconds)))
                .orElse(0);
        
        return lyricsWithTimestamps.getOrDefault(closestTimestamp, "Lyrics not found");
    }
    
      
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton audioButton;
    private javax.swing.JButton centerButton;
    private javax.swing.JButton fastforwardButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lyricsShow;
    private javax.swing.JLabel musicTimer;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton rewindButton;
    private javax.swing.JSlider volumeSlider;
    // End of variables declaration//GEN-END:variables
}
