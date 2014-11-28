#!/bin/bash

foldername=~/$1
filesuffix=jpg
raspistill="raspistill -v --nopreview"
logfile=${foldername}/shooting.log

mkdir $foldername

# normal
$raspistill -o $foldername/normal.$filesuffix &>>${logfile}

# Autwo wite balance
$raspistill -o $foldername/awb-auto.$filesuffix --awb auto  &>>${logfile}
$raspistill -o $foldername/awb-sun.$filesuffix --awb sun &>>${logfile}
$raspistill -o $foldername/awb-off.$filesuffix --awb off &>>${logfile}
$raspistill -o $foldername/awb-flash.$filesuffix --awb flash &>>${logfile}
$raspistill -o $foldername/awb-horizon.$filesuffix --awb horizon &>>${logfile}
$raspistill -o $foldername/awb-incandescent.$filesuffix --awb incandescent &>>${logfile}

# greyscale 
$raspistill -o $foldername/greyscale128128.$filesuffix -cfx 128:128 &>>${logfile}

# Kontrast
for count in {-5..5}
do
  ((param=$count*20))
  ((fileprefix=$count+15))
  ${raspistill} -o ${foldername}/contrast_${fileprefix}_${param}.${filesuffix} --contrast ${param} &>>${logfile}
done

# Brightness
# for count in {1..4}
# do
#   ((param=$count*20))
#   ((fileprefix=$count+10))
#   ${raspistill} -o ${foldername}/brightness_${fileprefix}_${param}.${filesuffix} --brightness ${param} &>>${logfile}
# done

# Saturation
# for count in {-5..5}
# do
#   ((param=$count*20))
#   ((fileprefix=$count+15))
#   ${raspistill} -o ${foldername}/saturation_${fileprefix}_${param}.${filesuffix} --saturation ${param} &>>${logfile}
# done

# Kontrast and Greyscale
for count in {-5..5}
do
  ((param=$count*20))
  ((fileprefix=$count+15))
  ${raspistill} -o ${foldername}/greyscaleandcontrast_${fileprefix}_${param}.${filesuffix} --contrast ${param} -cfx 128:128 &>>${logfile}
done

# Kontrast and Greyscale And Quality 75
for count in {-5..5}
do
  ((param=$count*20))
  ((fileprefix=$count+15))
  ${raspistill} -o ${foldername}/greyscaleandcontrast_quality75_${fileprefix}_${param}.${filesuffix} --contrast ${param} -cfx 128:128 --quality 75 &>>${logfile}
done

# Kontrast and Greyscale And Quality 50
for count in {-5..5}
do
  ((param=$count*20))
  ((fileprefix=$count+15))
  ${raspistill} -o ${foldername}/greyscaleandcontrast_quality50_${fileprefix}_${param}.${filesuffix} --contrast ${param} -cfx 128:128 --quality 50 &>>${logfile}
done
