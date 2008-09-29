#!/bin/sh

ant test -DserviceURL=http://cbvapp-d1008.nci.nih.gov:59480/wsrf/services/cagrid/CaDSRDataService -DtestDir=${1} 